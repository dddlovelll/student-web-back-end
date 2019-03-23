package com.qmkl.entity;

import java.util.Date;

/**
 * @program: qmkl
 * @description:
 * @author: ChenYu
 * @create: 2019-03-09 18:53
 **/
public class Report {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private String content;
    private Date date;
    private boolean state;

    public Report() {
    }


    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Report(Integer userId, Integer postId, String content, Date createTime, boolean state) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.date = createTime;
        this.state = state;
    }


}
