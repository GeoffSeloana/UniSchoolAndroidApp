package com.example.codetribe1.unischool.dto;

import java.io.Serializable;

/**
 * Created by CodeTRibe1 on 2015-03-29.
 */
public class GradeDTO implements Serializable {
    private Integer gradeID;
    private Integer gradeNumber;
    private int schoolID;

    public GradeDTO() {
    }

    public GradeDTO(Integer gradeID, Integer gradeNumber, int schoolID) {
        this.gradeID = gradeID;
        this.gradeNumber = gradeNumber;
        this.schoolID = schoolID;
    }

    public Integer getGradeID() {
        return gradeID;
    }

    public void setGradeID(Integer gradeID) {
        this.gradeID = gradeID;
    }

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }
}
