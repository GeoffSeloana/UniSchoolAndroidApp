package com.example.codetribe1.unischool.dto;

import java.io.Serializable;

/**
 * Created by CodeTRibe1 on 2015-03-29.
 */
public class StudentDTO implements Serializable {
    private Integer studentID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private String schoolID;
    private String gradeID;
    private String email;

    public StudentDTO() {
    }

    public StudentDTO(Integer studentID, String firstName, String middleName, String lastName, String password, String schoolID, String gradeID, String email) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.password = password;
        this.schoolID = schoolID;
        this.gradeID = gradeID;
        this.email = email;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
