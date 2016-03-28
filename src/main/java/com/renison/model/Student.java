package com.renison.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "student")
public class Student extends BaseModel {
    public static enum Gender {
        FEMALE,
        MALE
    }

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "email")
    private String email;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE) // have to put it to avoid `trailing junk on timestamp` error
    private Date dateOfBirth;
    @Column(name = "university")
    private String university;
    @Column(name = "currentMajor")
    private String currentMajor;

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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCurrentMajor() {
        return currentMajor;
    }

    public void setCurrentMajor(String currentMajor) {
        this.currentMajor = currentMajor;
    }

}
