package com.renison.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "category")
public class Category extends BaseModel {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "test_id", nullable = false)
    @JsonBackReference
    private Test test;

    @OneToMany(mappedBy = "category")
    @Cascade({ CascadeType.PERSIST, CascadeType.SAVE_UPDATE, CascadeType.DELETE })
    // this field is not serialized by Jackson http://stackoverflow.com/questions/21708339/avoid-jackson-serialization-on-non-fetched-lazy-objects 
    private List<TestComponent> testComponents = new ArrayList<TestComponent>();

    @Column(name = "timeAllowed")
    private double timeAllowed;

    @Column(name = "ordering")
    private int ordering;

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

    public double getTimeAllowed() {
        return timeAllowed;
    }

    public void setTimeAllowed(double timeAllowed) {
        this.timeAllowed = timeAllowed;
    }
}
