package com.renison.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@DiscriminatorValue(value = ComponentType.Value.SHORT_ANSWER)
@Table(name = "short_answer")
@JsonTypeName(ComponentType.Value.SHORT_ANSWER)
public class ShortAnswer extends Question {
	@JsonView(View.Admin.class)
	@Override
	public List<Answer> getAnswers() {
		return super.getAnswers();
	}
}
