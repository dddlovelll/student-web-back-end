package com.qmkl.entity;

import java.util.Date;

public class MyCollectResult {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private Date createTime;
    private Boolean state;
    private PostResult postResult;

    public MyCollectResult() {
    }


    @Override
    public String toString() {
        return "MyCollectResult{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", createTime=" + createTime +
                ", state=" + state +
                ", postResult=" + postResult +
                '}';
    }

    public PostResult getPostResult() {
        return postResult;
    }

    public void setPostResult(PostResult postResult) {
        this.postResult = postResult;
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
