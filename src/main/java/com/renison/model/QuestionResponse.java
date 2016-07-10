package com.renison.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "question_response", uniqueConstraints = @UniqueConstraint(columnNames = { "test_session_id",
		"question_id" }))
// A response to a question can have many response content,
// since a question might contain multiple sub parts ( i.e. matching question)
public class QuestionResponse extends BaseModel {

	@ManyToOne
	@JsonView(View.Admin.class)
	@JoinColumn(name = "test_session_id", nullable = false)
	@NotNull
	@JsonBackReference("testSession")
	private TestSession testSession;

	@ManyToOne
	@JsonView(View.Admin.class)
	@JoinColumn(name = "question_id", nullable = false)
	@NotNull
	// @JsonManagedReference("question")
	private Question question;

	@OneToMany(mappedBy = "questionResponse")
	@JsonView(View.Admin.class)
	@Cascade({ CascadeType.ALL })
	private Set<ResponseContent> responseContents = new HashSet<>();

	public QuestionResponse() {

	}

	public QuestionResponse(TestSession testSession, Question question) {
		this.question = question;
		this.testSession = testSession;
	}

	@JsonIgnore
	public int getQuestionScore() {
		return getQuestion().getScore(this);
	}

	public TestSession getTestSession() {
		return testSession;
	}

	public void setTestSession(TestSession testSession) {
		this.testSession = testSession;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Set<ResponseContent> getResponseContents() {
		return responseContents;
	}

	public void setResponseContents(Set<ResponseContent> responseContents) {
		this.responseContents = responseContents;
		for (ResponseContent responseContent : responseContents) {
			responseContent.setQuestionResponse(this);
		}
	}
}
