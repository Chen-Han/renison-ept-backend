package com.renison.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = ComponentType.Value.FREE_TEXT)
@Table(name = "free_text")
@JsonTypeName(ComponentType.Value.FREE_TEXT)
public class FreeText extends Question {
}
