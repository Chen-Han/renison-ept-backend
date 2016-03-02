package com.renison.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
// for all child table, info in child and parent are retrieved via a `join` statement
// e.g. select * from question join multiple_choice on question.id = multiple_choice.id
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question extends TestComponent {

    @Column(name = "content")
    private String content;

    @Transient
    public static final boolean scorable = false; //whether question can be scored

    @Column(name = "ordering")
    private int ordering;

    @OneToMany
    @JoinTable(name = "question_answer", joinColumns = {
            @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "answer_id", referencedColumnName = "id", nullable = false) })
    @Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE })
    private List<Answer> answers = new ArrayList<Answer>();

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

}
