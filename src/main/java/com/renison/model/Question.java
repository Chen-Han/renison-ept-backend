package com.renison.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
// for all child table, info in child and parent are retrieved via a `join`
// statement
// e.g. select * from question join multiple_choice on question.id =
// multiple_choice.id
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question extends TestComponent {

	@Column(name = "content", columnDefinition = "TEXT")
	@JsonView(View.Public.class)
	private String content;

	@Transient
	public static final boolean scorable = false; // whether question can be
													// scored
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonView(View.Public.class)
	@JoinTable(name = "question_answer", joinColumns = {
			@JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "answer_id", referencedColumnName = "id", nullable = false) })
	@Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE, CascadeType.DETACH })
	private List<Answer> answers = new ArrayList<Answer>();

	@OneToMany(mappedBy = "question", orphanRemoval = true)
	@JsonBackReference("questionResponses")
	private Set<QuestionResponse> questionResponses;

	@Transient
	private boolean isSaved;

	@Transient
	@JsonView(View.Student.class)
	private Set<Map<String, String>> response;

	@JsonIgnore
	public int getScore(QuestionResponse questionResponse) {
		Set<ResponseContent> responseContents = questionResponse.getResponseContents();
		if (responseContents == null || responseContents.isEmpty()) {
			return 0;
		}
		ResponseContent responseContent = responseContents.iterator().next();// one
																				// response
																				// for
																				// now
		// response matches any correct answer?
		boolean isCorrect = getAnswers().stream().filter((a) -> a.isCorrect()).anyMatch((a) -> {
			return a.getContent().trim().toLowerCase().equals(responseContent.getText().trim().toLowerCase());
		});
		if (isCorrect) {
			return 1;
		} else {
			return 0;
		}
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<QuestionResponse> getQuestionResponses() {
		return questionResponses;
	}

	public void setQuestionResponses(Set<QuestionResponse> questionResponses) {
		this.questionResponses = questionResponses;
	}

	public static boolean isScorable() {
		return scorable;
	}

	@JsonView(View.Student.class)
	@JsonGetter(value = "isSaved")
	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	public void addResponse(String text) {
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("text", text);
		if (getResponse() == null) {
			setResponse(new HashSet<>());
		}
		getResponse().add(responseObj);
	}

	@JsonGetter("response")
	@JsonView(View.Student.class)
	public Set<Map<String, String>> getResponse() {
		return response;
	}

	public void setResponse(Set<Map<String, String>> response) {
		this.response = response;
	}

	@Override
	public void detach() {
		super.detach();
		this.setQuestionResponses(new HashSet<>());
		for (Answer a : getAnswers()) {
			a.detach();
		}
		ArrayList<Answer> list = new ArrayList<>();
		list.addAll(getAnswers());
		this.setAnswers(list);
	}
}
