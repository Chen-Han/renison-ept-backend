package com.renison.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;
import com.renison.jackson.View.Admin;

@Entity
@Table(name = "category")
public class Category extends BaseModel {
	@ManyToOne
	@JsonView(View.Public.class)
	@NotNull
	@JoinColumn(name = "test_id", nullable = false)
	@JsonBackReference("test")
	private Test test;

	@Column(name = "name")
	@JsonView(View.Public.class)
	private String name;

	@OneToMany(mappedBy = "category")
	// see here for the error cannot fetch multiple bags simultaneously
	// http://stackoverflow.com/questions/4334970/hibernate-cannot-simultaneously-fetch-multiple-bags
	@LazyCollection(LazyCollectionOption.TRUE)
	@JsonView(View.Student.class) // student only, admin makes another request
									// to retrieve info
	@Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE, CascadeType.DETACH })
	// no managed reference here
	// http://stackoverflow.com/questions/32070139/can-not-handle-managed-back-reference-defaultreference-in-jackson-for-composit
	// this field is not serialized by Jackson
	// http://stackoverflow.com/questions/21708339/avoid-jackson-serialization-on-non-fetched-lazy-objects
	private List<TestComponent> testComponents = new ArrayList<TestComponent>();

	@Column(name = "timeAllowed")
	@JsonView(View.Public.class)
	private double timeAllowed; // TODO change this to BigDecimal

	@Column(name = "ordering")
	@JsonView(View.Public.class)
	private int ordering;

	@OneToMany(mappedBy = "category")
	@JsonBackReference("progresses")
	@Cascade({ CascadeType.DELETE })
	private List<Progress> progresses;

	@OneToMany(mappedBy = "category")
	// should not have orphan removal, see
	// http://stackoverflow.com/questions/5587482/hibernate-a-collection-with-cascade-all-delete-orphan-was-no-longer-referenc
	@JsonIgnore
	@Cascade({ CascadeType.DELETE })
	private Set<CategoryScore> categoryScores = new HashSet<>(); // in the test

	@Transient
	@JsonView(View.Student.class)
	private List<String> allCategories = new ArrayList<>();

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<TestComponent> getTestComponents() {
		return testComponents;
	}

	public void setTestComponents(List<TestComponent> testComponents) {
		this.testComponents = testComponents;
	}

	public void addTestComponent(TestComponent testComponent) {
		testComponent.setCategory(this);
		getTestComponents().add(testComponent);
	}

	public double getTimeAllowed() {
		return timeAllowed;
	}

	public long getTimeAllowedInSeconds() {
		return Math.round((timeAllowed * 60.0));
	}

	public void setTimeAllowed(double timeAllowed) {
		this.timeAllowed = timeAllowed;
	}

	public List<Progress> getProgresses() {
		return progresses;
	}

	public void setProgresses(List<Progress> progresses) {
		this.progresses = progresses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CategoryScore> getCategoryScores() {
		return categoryScores;
	}

	public void setCategoryScores(Set<CategoryScore> categoryScores) {
		this.categoryScores = categoryScores;
	}

	public List<String> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<String> allCategories) {
		this.allCategories = allCategories;
	}

	@JsonView(Admin.class)
	// count the # of questions in the category
	public int getTotalScore() {
		return (int) getTestComponents().stream().filter((t) -> {
			return (t instanceof MultipleChoice) || (t instanceof ShortAnswer) || (t instanceof TrueFalse);
		}).count();
	}

	@Override
	public void detach() {
		super.detach();
		this.setProgresses(new ArrayList<>());
		this.setCategoryScores(new HashSet<>());
		for (TestComponent t : getTestComponents()) {
			t.detach();
		}
	}

}
