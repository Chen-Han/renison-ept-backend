package com.renison.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "school")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class School extends BaseModel {

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "school")
    //    @JsonManagedReference
    private Set<TestTaker> testTakers;

    public Set<TestTaker> getTestTakers() {
        return testTakers;
    }

    public void setTestTakers(Set<TestTaker> testTakers) {
        this.testTakers = testTakers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
