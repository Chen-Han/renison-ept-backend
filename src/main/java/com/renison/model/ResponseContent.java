package com.renison.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "response_content")
public class ResponseContent extends BaseModel {
    @ManyToOne
    @JsonView(View.Public.class)
    @JoinColumn(name = "question_response_id", nullable = false)
    @NotNull
    @JsonBackReference("questionResponse")
    private QuestionResponse questionResponse;

    @OneToOne
    @JsonView(View.Public.class)
    @JoinColumn(name = "answer_id", nullable = true)
    private Answer answerResponded;

    @Column(name = "text", nullable = true)
    @JsonView(View.Public.class)
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
