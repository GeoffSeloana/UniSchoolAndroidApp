package com.example.codetribe1.unischool.dto.transfer;

import com.example.codetribe1.unischool.dto.NewsDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodeTRibe1 on 2015-04-10.
 */
public class RequestDTO implements Serializable{
    private Integer newsID;
    private NewsDTO newsDTO;
    List<NewsDTO>newsList;

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
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
