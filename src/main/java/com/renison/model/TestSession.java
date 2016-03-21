package com.renison.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "test_session")
public class TestSession extends BaseModel {
    @Column(name = "last_active_at", columnDefinition = "timestamp with time zone", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActiveAt;

    @OneToMany(mappedBy = "testSession")
    private List<Progress> progresses = new ArrayList<Progress>();

    @OneToMany(mappedBy = "testSession")
    private Set<QuestionResponse> questionResponses = new HashSet<QuestionResponse>();

    @Column(name = "score")
    private double score;
    
	@OneToOne
	@JoinColumn(name="student_id")
	private Student student;
	
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="test_id")
    private Test test;

    public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Progress> getProgresses() {
        return progresses;
    }

    public void setProgresses(List<Progress> progresses) {
        this.progresses = progresses;
    }

    public Set<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(Set<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(Date lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

}
