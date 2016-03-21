package com.renison.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "question_response")
public class QuestionResponse extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "test_session_id", nullable = false)
	@NotNull
	@JsonBackReference("testSession")
	private TestSession testSession;

	@OneToOne
	@JoinColumn(name = "question_id", nullable = false)
	@NotNull
	@JsonBackReference("question")
	private Question question;

	@OneToMany(mappedBy = "questionResponse")
	private Set<ResponseContent> responseContents;

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
	}
}
