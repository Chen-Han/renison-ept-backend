package com.renison.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@DiscriminatorValue(value = ComponentType.Value.TRUE_FALSE)
@Table(name = "true_false")
@JsonTypeName(ComponentType.Value.TRUE_FALSE)
public class TrueFalse extends Question {

    @Column(name = "answer", nullable = false)
    @NotNull
    @JsonView(View.Admin.class)
    private boolean answer;

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    @JsonIgnore
    public void setAnswers(List<Answer> answers) {

    }

    @Override
    @JsonIgnore
    public List<Answer> getAnswers() {
        return null;
    }
}
