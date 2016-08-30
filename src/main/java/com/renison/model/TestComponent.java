package com.renison.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "test_component")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "component_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "componentType")
@JsonSubTypes({ @Type(value = HtmlComponent.class), @Type(value = VideoComponent.class),
		@Type(value = MultipleChoice.class), @Type(value = FreeText.class), @Type(value = TrueFalse.class),
		@Type(value = ShortAnswer.class), })
public abstract class TestComponent extends BaseModel {

	@Column(name = "component_type", updatable = false, insertable = false) // without
																			// this
																			// hibernate
																			// will
																			// try
																			// to
																			// generate
																			// sql
																			// like
																			// insert
																			// (type,type
																			// ...)
																			// which
																			// wouldn't
																			// run
	@JsonView(View.Public.class)
	@Enumerated(EnumType.STRING)
	private ComponentType componentType;

	@Column(name = "ordering")
	@JsonView(View.Public.class)
	private int ordering;

	@ManyToOne
	@JsonView(View.Public.class)
	@NotNull
	@JoinColumn(name = "category_id", nullable = false)
	@JsonBackReference("testComponents")
	private Category category;

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	@Override
	public void detach() {
		super.detach();
	}
}
