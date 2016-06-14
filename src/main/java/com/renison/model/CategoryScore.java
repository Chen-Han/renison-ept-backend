package com.renison.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "category_score")
public class CategoryScore extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "test_session_id", nullable = false)
	@JsonIgnore
	@NotNull
	private TestSession testSession;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@JsonIgnore
	@NotNull
	private Category category;

	@Column(name = "score")
	@JsonView({ View.Public.class })

	private BigDecimal score;

	public CategoryScore() {

	}

	public CategoryScore(TestSession testSession, Category category, BigDecimal score) {
		setTestSession(testSession);
		setCategory(category);
		setScore(score);
	}

	public CategoryScore(TestSession testSession, Category category, int score) {
		this(testSession, category, new BigDecimal(score));
	}

	public TestSession getTestSession() {
		return testSession;
	}

	public void setTestSession(TestSession testSession) {
		this.testSession = testSession;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score.setScale(5, BigDecimal.ROUND_HALF_UP);
	}
}