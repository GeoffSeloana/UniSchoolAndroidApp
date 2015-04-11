package com.example.codetribe1.unischool.dto.transfer;

import com.example.codetribe1.unischool.dto.GradeDTO;
import com.example.codetribe1.unischool.dto.NewsDTO;
import com.example.codetribe1.unischool.dto.StudentDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodeTRibe1 on 2015-04-10.
 */
public class ResponseDTO implements Serializable {
    private NewsDTO newsDTO;

    List<NewsDTO>newsList;
    List<StudentDTO>studentList;
    List<GradeDTO>gradeList;

    private Integer statusCode;
    private String message;

    public String getMessage() {
        return message;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }

    public List<GradeDTO> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<GradeDTO> gradeList) {
        this.gradeList = gradeList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public NewsDTO getNewsDTO() {
        return newsDTO;
    }

    public void setNewsDTO(NewsDTO newsDTO) {
        this.newsDTO = newsDTO;
    }

    public List<NewsDTO> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsDTO> newsList) {
        this.newsList = newsList;
    }
}
