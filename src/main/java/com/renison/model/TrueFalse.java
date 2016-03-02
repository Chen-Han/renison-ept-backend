package com.renison.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = ComponentType.Value.TRUE_FALSE)
@Table(name = "true_false")
@JsonTypeName(ComponentType.Value.TRUE_FALSE)
public class TrueFalse extends Question {

    @Column(name = "answer", nullable = false)
    @NotNull
    private boolean answer;

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
