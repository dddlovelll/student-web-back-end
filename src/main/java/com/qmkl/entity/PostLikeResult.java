package com.qmkl.entity;

import java.util.Date;

public class PostLikeResult {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private String nickName;
    private Date createTime;

    @Override
    public String toString() {
        return "PostLikeResult{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", nickName='" + nickName + '\'' +
                ", createTime=" + createTime +
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
