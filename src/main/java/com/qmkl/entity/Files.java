package com.qmkl.entity;

import java.io.Serializable;
import java.util.Date;

public class Files  implements Serializable{

    private Integer id;
    private long size;
    private String md5;
    private String ip;
    private long uploadTime;
    private Date createdAt;
    private Date updatedAt;
    private Integer uploadUserId;
    private College college;
    private String spath;
    private Enum state; // enum('NORMAL','DELETED')


    public Files(long size, String md5, String ip, Integer uploadUserId, String spath) {
        this.size = size;
        this.md5 = md5;
        this.ip = ip;
        this.uploadUserId = uploadUserId;
        this.spath = spath;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.uploadTime = System.currentTimeMillis();
    }

    public Files() {
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", size=" + size +
                ", md5='" + md5 + '\'' +
                ", ip='" + ip + '\'' +
                ", uploadTime=" + uploadTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", uploadUserId=" + uploadUserId +
                ", college=" + college +
                ", spath='" + spath + '\'' +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
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

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public String getSpath() {
        return spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
    }

    public Enum getState() {
        return state;
    }

    public void setState(Enum state) {
        this.state = state;
    }
}
