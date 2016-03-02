package com.renison.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer extends BaseModel {
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setToTrue() {
        this.isCorrect = true;
    }

    public void setToFalse() {
        this.isCorrect = false;
    }
}
