package com.renison.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.renison.jackson.View;

@Entity
@Table(name = "test_taker")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TestTaker extends BaseModel {
    @Column(name = "first_name")
    @JsonView(View.Public.class)
    private String firstName;

    @Column(name = "last_name")
    @JsonView(View.Public.class)
    private String lastName;

    @ManyToOne
    @JsonView(View.Public.class)
    @JoinColumn(name = "school_id")
    @JsonBackReference("school_testTaker")
    private School school;

    @OneToMany(mappedBy = "testTaker")
    @JsonView(View.Public.class)
    //    @JsonManagedReference("random")
    private Set<Pencil> pencils = new HashSet<>();

    public Set<Pencil> getPencils() {
        return pencils;
    }

    public void setPencils(Set<Pencil> pencils) {
        this.pencils = pencils;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}
