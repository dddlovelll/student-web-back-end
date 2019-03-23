package com.qmkl.entity;

import java.util.Date;

public class PostLike {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private Date createTime;
    private boolean state;

    public PostLike() {
    }


    public PostLike(Integer userId, Integer postId, Date createTime, boolean state) {
        this.userId = userId;
        this.postId = postId;
        this.createTime = createTime;
        this.state = state;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", createTime=" + createTime +
                ", state=" + state +
                '}';
    }
}
