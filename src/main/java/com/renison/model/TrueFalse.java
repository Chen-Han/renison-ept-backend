package com.renison.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = ComponentType.Value.TRUE_FALSE)
@Table(name = "true_false")
@JsonTypeName(ComponentType.Value.TRUE_FALSE)
public class TrueFalse extends Question {
}
