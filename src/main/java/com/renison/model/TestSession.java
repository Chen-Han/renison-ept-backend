package com.renison.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.criterion.Restrictions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.JsonEptView;
import com.renison.jackson.View;
import com.renison.jackson.View.Admin;

@Entity
@Table(name = "test_session", uniqueConstraints = @UniqueConstraint(columnNames = { "test_id", "student_id" }))
public class TestSession extends BaseModel {

	@OneToMany(mappedBy = "testSession")
	@JsonView(View.Public.class)
	@OrderBy("id DESC")
	@Cascade({ CascadeType.ALL })
	private List<Progress> progresses = new ArrayList<Progress>();

	@OneToMany(mappedBy = "testSession")
	@JsonView(View.Public.class)
	@Cascade({ CascadeType.ALL })
	private Set<QuestionResponse> questionResponses = new HashSet<QuestionResponse>();

	@OneToOne
	@JsonView(View.Public.class)
	@JoinColumn(name = "student_id")
	@JsonEptView(roles = { "ADMIN" })
	@Cascade({ CascadeType.ALL })
	private Student student;

	@JsonBackReference
	@ManyToOne
	@JsonView(View.Public.class)
	@JoinColumn(name = "test_id")
	private Test test;

	@OneToMany(mappedBy = "testSession")
	@JsonView(Admin.class)
	@Cascade({ CascadeType.ALL })
	private Set<CategoryScore> categoryScores = new HashSet<>();

	@Column(name = "test_submitted", columnDefinition = "boolean DEFAULT FALSE")
	@JsonView(View.Public.class)
	private boolean testSubmitted;

	/**
	 * rescores all questions in this test for this student
	 * 
	 * @param session
	 */
	@JsonIgnore
	public void scoreTest(Session session) {
		// clear all previous scores
		for (CategoryScore score : getCategoryScores()) {
			score.getCategory().getCategoryScores().remove(score);
			session.delete(score);
		}
		getCategoryScores().clear(); // clear all existing category scores
		for (Category category : test.getCategories()) {
			int totalScore = 0;
			for (TestComponent question : category.getTestComponents()) {
				if (!(question instanceof Question)) {
					continue;
				}
				question = (Question) question;
				QuestionResponse questionResponse = (QuestionResponse) session.createCriteria(QuestionResponse.class)
						.add(Restrictions.eq("testSession", this)).add(Restrictions.eq("question", question))
						.uniqueResult();
				if (questionResponse == null) {
					continue;
				}
				totalScore += questionResponse.getQuestionScore();
			}
			CategoryScore categoryScore = new CategoryScore(this, category, totalScore);
			category.getCategoryScores().add(categoryScore);
			getCategoryScores().add(categoryScore);
		}
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Progress> getProgresses() {
		return progresses;
	}

	public void setProgresses(List<Progress> progresses) {
		this.progresses = progresses;
	}

	public void addProgress(Progress progress) {
		progress.setTestSession(this);
		getProgresses().add(progress);
	}

	public void saveProgresses(Session session) {
		for (Progress progress : getProgresses()) {
			session.saveOrUpdate(progress);
		}
	}

	public Progress getLatestProgress() {
		// the latest progress must not have endAt timestamp set
		// return progresses.stream().filter((p) -> p.getEndAt() ==
		// null).findAny().get();
		if (getProgresses().isEmpty())
			return null;
		// note that progresses are sorted by startAt DESC
		// so that the first item is the latest progress
		return getProgresses().iterator().next();
	}

	@JsonIgnore
	public Category getLatestCategory() {
		return getLatestProgress().getCategory();
	}

	@JsonIgnore
	// returns time left for current category in seconds
	public long getTimeLeftForCurrentProgress() {
		return getLatestProgress().getTimeLeftInSeconds();
	}

	public Set<QuestionResponse> getQuestionResponses() {
		return questionResponses;
	}

	public void setQuestionResponses(Set<QuestionResponse> questionResponses) {
		this.questionResponses = questionResponses;
	}

	public boolean isTestSubmitted() {
		return testSubmitted;
	}

	public boolean isTestStarted() {
		return getLatestProgress() != null;
	}

	public void markAsSubmitted() {
		testSubmitted = true;
	}

	public void markAsUnsubmitted() {
		testSubmitted = false;
	}

	public Set<CategoryScore> getCategoryScores() {
		return categoryScores;
	}

	public void setCategoryScores(Set<CategoryScore> categoryScores) {
		this.categoryScores = categoryScores;
	}

	// gets the student's score for the test
	public int getScore() {
		int score = 0;
		for (CategoryScore categoryScore : getCategoryScores()) {
			score += categoryScore.getScore().intValue();
		}
		return score;
	}
}
