package com.renison.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "html_component")
@DiscriminatorValue(value = ComponentType.Value.HTML)
@JsonTypeName(ComponentType.Value.HTML)
public class HtmlComponent extends TestComponent {
	@Column(name = "content", columnDefinition = "LONGTEXT")
	@JsonView(View.Public.class)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
