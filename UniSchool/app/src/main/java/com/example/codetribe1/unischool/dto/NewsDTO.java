package com.example.codetribe1.unischool.dto;

import java.io.Serializable;

/**
 * Created by CodeTRibe1 on 2015-03-28.
 */
public class NewsDTO implements Serializable {

    private Integer newsID;
    private String title;
    private String subTitle;
    private String details;
    private Double longitude;
    private Double latitude;
    private int schoolID;

    public NewsDTO() {
    }

    public NewsDTO(Integer newsID, String title, String subTitle, String details, Double longitude, Double latitude, int schoolID) {
        this.newsID = newsID;
        this.title = title;
        this.subTitle = subTitle;
        this.details = details;
        this.longitude = longitude;
        this.latitude = latitude;
        this.schoolID = schoolID;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }
}
