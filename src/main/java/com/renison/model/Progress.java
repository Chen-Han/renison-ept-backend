package com.renison.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
@Table(name = "progress")
public class Progress extends BaseModel {
	@ManyToOne
	@JsonView(View.Public.class)
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull
	// @JsonManagedReference("category") no managed reference here
	private Category category;

	@Column(name = "start_at", nullable = false)
	@JsonView(View.Public.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startAt = new Date(); // defaults to now

	@Column(name = "end_at")
	@JsonView(View.Public.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endAt;

	@ManyToOne
	@JsonView(View.Public.class)
	@JoinColumn(name = "test_session_id")
	private TestSession testSession;

	public Progress(TestSession testSession, Category category) {
		setTestSession(testSession);
		setCategory(category);
	}

	public Progress() {

	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	@JsonIgnore
	public Date getExpectedEndAt() {
		// vals in milisec
		long startAt = this.startAt.getTime();
		long timeAllowed = getCategory().getTimeAllowedInSeconds() * 1000;
		return new Date(startAt + timeAllowed);
	}

	@JsonIgnore
	public long getTimeLeftInSeconds() {
		if (this.getEndAt() != null) {
			throw new IllegalStateException("Progress already ended, cannot get time left");
		}
		long startAt = this.getStartAt().getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long timePassed = (now - startAt);
		long timeAllowed = (long) this.getCategory().getTimeAllowedInSeconds();
		return Math.max(0, timeAllowed - timePassed);
	}

	public TestSession getTestSession() {
		return testSession;
	}

	public void setTestSession(TestSession testSession) {
		this.testSession = testSession;
	}

	@PrePersist
	@PreUpdate
	public void onPrePersistOrUpdate() {
		System.out.println("Pre insert hook works for Progress");
		super.prePersist();
		super.preUpdate();
		startAt = new Date();
	}

}
