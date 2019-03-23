package com.qmkl.entity;

import java.util.Date;

public class UserAuths {
    private Integer id;
    private String platform;
    private String platformId;
    private Date createdAt;
    private Date updatedAt;
    private Integer userId;


    public UserAuths(String platform, String platformId, Date createdAt, Date updatedAt) {
        this.platform = platform;
        this.platformId = platformId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public UserAuths() {
    }

    @Override
    public String toString() {
        return "UserAuths{" +
                "id=" + id +
                ", platform='" + platform + '\'' +
                ", platformId='" + platformId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
