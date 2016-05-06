package com.renison.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.Session;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.JsonEptView;
import com.renison.jackson.View;

@Entity
@Table(name = "test_session")
public class TestSession extends BaseModel {

	@OneToMany(mappedBy = "testSession", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JsonView(View.Public.class)
	@OrderBy("startAt DESC")
	private List<Progress> progresses = new ArrayList<Progress>();

	@OneToMany(mappedBy = "testSession")
	@JsonView(View.Public.class)
	private Set<QuestionResponse> questionResponses = new HashSet<QuestionResponse>();

	@Column(name = "score")
	@JsonView(View.Admin.class)
	private double score;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JsonView(View.Public.class)
	@JoinColumn(name = "student_id")
	@JsonEptView(roles = { "ADMIN" })
	@JsonManagedReference
	private Student student;

	@JsonBackReference
	@ManyToOne
	@JsonView(View.Public.class)
	@JoinColumn(name = "test_id")
	private Test test;

	@Column(name = "test_submitted", columnDefinition = "boolean DEFAULT FALSE")
	@JsonView(View.Public.class)
	private boolean testSubmitted;

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
		return getProgresses().iterator().next();
	}

	public Set<QuestionResponse> getQuestionResponses() {
		return questionResponses;
	}

	public void setQuestionResponses(Set<QuestionResponse> questionResponses) {
		this.questionResponses = questionResponses;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isTestSubmitted() {
		return testSubmitted;
	}

	public void markAsSubmitted() {
		testSubmitted = true;
	}

}
