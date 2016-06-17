package com.renison.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = ComponentType.Value.MULTIPLE_CHOICE)
@Table(name = "multiple_choice")
@JsonTypeName(ComponentType.Value.MULTIPLE_CHOICE)
public class MultipleChoice extends Question {
}