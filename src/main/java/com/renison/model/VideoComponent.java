package com.renison.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "video_component")
@DiscriminatorValue(value = ComponentType.Value.VIDEO)
@JsonTypeName(ComponentType.Value.VIDEO)
public class VideoComponent extends TestComponent {
	@Column(name = "content")
	@JsonView(View.Public.class)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
