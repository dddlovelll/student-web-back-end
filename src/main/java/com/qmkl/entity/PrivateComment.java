package com.qmkl.entity;

import java.util.Date;

/**
 * @program: qmkl
 * @description:
 * @author: ChenYu
 * @create: 2019-03-09 20:47
 **/
public class PrivateComment {
    private Integer id;
    private Integer userId1;
    private Integer userId2;
    private Integer postId;
    private String content;
    private Date date;

    public PrivateComment() {
    }
    public PrivateComment(Integer userId,Integer userId2, Integer postId, String content, Date date) {
        this.userId1 = userId;
        this.userId2 = userId2;
        this.postId = postId;
        this.content = content;
        this.date = date;
    }
    @Override
    public String toString() {
        return "PrivateComment{" +
                "id=" + id +
                ", userId1=" + userId1 +
                ", userId2=" + userId2 +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId1() {
        return userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
