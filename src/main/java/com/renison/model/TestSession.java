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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.JsonEptView;
import com.renison.jackson.View;

@Entity
@Table(name = "test_session")
public class TestSession extends BaseModel {

    @OneToMany(mappedBy = "testSession")
    @JsonView(View.Public.class)
    @OrderBy("startAt DESC")
    @Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE })
    private List<Progress> progresses = new ArrayList<Progress>();

    @OneToMany(mappedBy = "testSession")
    @Where(clause = "end_at is NULL ") // end_at = NULL does not work in this case
    @JsonView(View.Public.class)
    private List<Progress> latestProgresses = new ArrayList<>();

    @OneToMany(mappedBy = "testSession")
    @JsonView(View.Public.class)
    private Set<QuestionResponse> questionResponses = new HashSet<QuestionResponse>();

    @Column(name = "score")
    @JsonView(View.Admin.class)
    private double score;

    @OneToOne
    @JsonView(View.Public.class)
    @JoinColumn(name = "student_id")
    @Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE })
    @JsonEptView(roles = { "ADMIN" })
    private Student student;

    @JsonBackReference
    @ManyToOne
    @JsonView(View.Public.class)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "test_submitted", columnDefinition = "boolean DEFAULT FALSE")
    @JsonView(View.Public.class)
    private boolean testSubmitted;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

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

    public void addProgress(Progress progress) {
        getProgresses().add(progress);
    }

    private List<Progress> getLatestProgresses() {
        return latestProgresses;
    }

    public Progress getLatestProgress() {
        // the latest progress must not have endAt timestamp set
        //        return progresses.stream().filter((p) -> p.getEndAt() == null).findAny().get();
        if (getLatestProgresses().isEmpty())
            return null;
        return getLatestProgresses().iterator().next();
    }

    public void setLatestProgresses(List<Progress> latestProgresses) {
        this.latestProgresses = latestProgresses;
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

    public boolean getTestSubmitted() {
        return testSubmitted;
    }

    public void setTestSubmitted(boolean testSubmitted) {
        this.testSubmitted = testSubmitted;
    }

}
