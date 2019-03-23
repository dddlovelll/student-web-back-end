package com.qmkl.entity;

import java.util.Date;

public class PrivateCommentResult {
    private Integer id;
    private Integer userId1;
    private Integer userId2;
    private Integer postId;
    private String content;
    private Date date;
    private String nickName1;
    private String nickName2;

    @Override
    public String toString() {
        return "PrivateCommentResult{" +
                "id=" + id +
                ", userId1=" + userId1 +
                ", userId2=" + userId2 +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", nickName1='" + nickName1 + '\'' +
                ", nickName2='" + nickName2 + '\'' +
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

    public String getNickName1() {
        return nickName1;
    }

    public void setNickName1(String nickName1) {
        this.nickName1 = nickName1;
    }

    public String getNickName2() {
        return nickName2;
    }

    public void setNickName2(String nickName2) {
        this.nickName2 = nickName2;
    }
}
