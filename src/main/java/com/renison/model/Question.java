package com.renison.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View;

@Entity
// for all child table, info in child and parent are retrieved via a `join`
// statement
// e.g. select * from question join multiple_choice on question.id =
// multiple_choice.id
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question extends TestComponent {

	@Column(name = "content")
	@JsonView(View.Public.class)
	private String content;

	@Transient
	public static final boolean scorable = false; // whether question can be
													// scored

	@Column(name = "ordering")
	@JsonView(View.Public.class)
	private int ordering;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonView(View.Public.class)
	@JoinTable(name = "question_answer", joinColumns = {
			@JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "answer_id", referencedColumnName = "id", nullable = false) })
	@Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private List<Answer> answers = new ArrayList<Answer>();

	@OneToMany(mappedBy = "question")
	@JsonBackReference("questionResponses")
	private Set<QuestionResponse> questionResponses;

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public Set<QuestionResponse> getQuestionResponses() {
		return questionResponses;
	}

	public void setQuestionResponses(Set<QuestionResponse> questionResponses) {
		this.questionResponses = questionResponses;
	}

	public static boolean isScorable() {
		return scorable;
	}

}
