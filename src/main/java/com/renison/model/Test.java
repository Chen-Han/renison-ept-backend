package com.renison.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "test")
public class Test extends BaseModel {
	@Column(name = "name", nullable = false)
	@JsonView(View.Public.class)
	@NotNull
	private String name;

	@Column(name = "description", columnDefinition = "text")
	@JsonView(View.Public.class)
	private String description;

	@Column(name = "comment")
	@JsonView(View.Admin.class)
	private String comment;

	@OneToMany(mappedBy = "test")
	@JsonIgnore // we don't want to serialize or deserialize this
	@Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE, CascadeType.DETACH })
	@OrderBy("ordering ASC")
	private List<Category> categories = new ArrayList<Category>();

	@Column(name = "active", nullable = false)
	@JsonView(View.Public.class)
	@NotNull
	private boolean active = false;

	@OneToMany(mappedBy = "test")
	@JsonIgnore
	@Cascade({ CascadeType.DELETE })
	private Set<TestSession> testSessions = new HashSet<>();

	@JsonIgnore
	public Category getFirstCategory() {
		if (getCategories().isEmpty()) {
			return null;
		}
		return getCategories().get(0);
	}

	public Set<TestSession> getTestSessions() {
		return testSessions;
	}

	public void setTestSessions(Set<TestSession> testSessions) {
		this.testSessions = testSessions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public int getCategoryIndex(Category category) {
		return getCategories().indexOf(category);
	}

	public void addCategory(Category category) {
		category.setTest(this);
		getCategories().add(category);
	}

	public Category nextCategoryTo(Category category) {
		int currIndex = getCategoryIndex(category);
		if (currIndex >= 0 && currIndex < getCategories().size() - 1) {
			return getCategories().get(currIndex + 1);
		}
		return null;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonIgnore
	public int getTotalScore() {
		int totalScore = 0;
		for (Category category : this.getCategories()) {
			totalScore += category.getTotalScore();
		}
		return totalScore;
	}

	@Override
	public void detach() {
		super.detach();
		this.getTestSessions().clear();
		this.setActive(false);
		for (Category c : this.getCategories()) {
			c.detach();
		}
		// must create a new array list so that hibernate
		// does not think it's old
		ArrayList<Category> set = new ArrayList<>();
		set.addAll(this.getCategories());
		this.setCategories(set);
	}
}
