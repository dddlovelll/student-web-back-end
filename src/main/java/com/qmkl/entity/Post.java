package com.qmkl.entity;

import java.util.Date;

public class Post {
    private Integer id;
    private Integer userId;
    private String content;
    private Date createTime;
    private Integer likeNum;
    private Integer commentNum;
    private Integer classify;
    private Integer reportNum;

    public Post(Integer userId, String content, Date createTime, Integer likeNum, Integer commentNum) {
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
    }

    public Post(Integer userId, String content, Date createTime, Integer likeNum, Integer commentNum, Integer classify) {
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.classify = classify;
    }

    public Post() {
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", classify=" + classify +
                ", reportNum=" + reportNum +
                '}';
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }
}
