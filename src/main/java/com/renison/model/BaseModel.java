package com.renison.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.annotations.Expose;
import com.renison.jackson.View;
import com.renison.listener.BaseModelListener;

@MappedSuperclass
@EntityListeners(BaseModelListener.class)
public abstract class BaseModel implements Detachable {

	@Id
	@Column(name = "id")
	@JsonView(View.Public.class)
	@GenericGenerator(name = "POOLED_LO_ID_GENERATOR", strategy = "enhanced-sequence", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
			@org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "10"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1000") })
	@GeneratedValue(generator = "POOLED_LO_ID_GENERATOR")
	@Expose
	private Long id;

	@Column(name = "create_timestamp", nullable = false, updatable = false, insertable = true)
	@JsonView(View.Public.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp = new Date();

	@Column(name = "update_timestamp", nullable = false)
	@JsonView(View.Public.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp = new Date();

	public Long getId() {
		return id;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@PrePersist
	protected void prePersist() {
		System.out.println("updating create timestamp and updatetimestamp");
		createTimestamp = updateTimestamp = new Date();
	}

	@PreUpdate
	protected void preUpdate() {
		updateTimestamp = new Date();
	}

	public void preUpdateOrPersist() {
		System.out.println("called====");
		if (getCreateTimestamp() == null) {
			createTimestamp = new Date();
		}
		updateTimestamp = new Date();
	}

	protected void resetIdTimestamp() {
		this.setId(null);
		this.setCreateTimestamp(new Date());
		this.setUpdateTimestamp(new Date());
	}

	@Override
	public void detach() {
		this.resetIdTimestamp();
	}
}
