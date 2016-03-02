package com.renison.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = ComponentType.Value.SHORT_ANSWER)
@Table(name = "short_answer")
@JsonTypeName(ComponentType.Value.SHORT_ANSWER)
public class ShortAnswer extends Question {

}
