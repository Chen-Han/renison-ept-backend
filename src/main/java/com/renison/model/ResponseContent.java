package com.renison.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "response_content")
public class ResponseContent extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "question_response_id", nullable = false)
	@NotNull
	@JsonBackReference("questionResponse")
	private QuestionResponse questionResponse;

	@OneToOne
	@JoinColumn(name = "answer_id", nullable = true)
	// @JsonBackReference("answerResponded")
	private Answer answerResponded;

	@Column(name = "text", nullable = true)
	private String text;

	public QuestionResponse getQuestionResponse() {
		return questionResponse;
	}

	public void setQuestionResponse(QuestionResponse questionResponse) {
		this.questionResponse = questionResponse;
	}

	public Answer getAnswerResponded() {
		return answerResponded;
	}

	public void setAnswerResponded(Answer answerResponded) {
		this.answerResponded = answerResponded;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
