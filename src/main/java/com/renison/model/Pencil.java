package com.renison.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pencil")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pencil extends BaseModel {
    public enum PencilType {
        MECHANICAL,
        NORMAL
    }

    @ManyToOne
    @JoinColumn(name = "test_taker_id")
    @JsonBackReference("testTaker_pencil")
    private TestTaker testTaker;

    @Column(name = "pencil_type")
    private PencilType pencilType;

    public TestTaker getTestTaker() {
        return testTaker;
    }

    public void setTestTaker(TestTaker testTaker) {
        this.testTaker = testTaker;
    }

    public PencilType getPencilType() {
        return pencilType;
    }

    public void setPencilType(PencilType pencilType) {
        this.pencilType = pencilType;
    }
}
