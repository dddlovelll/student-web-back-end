package com.qmkl.entity;

import java.util.Date;

public class UploadFile {
    private Integer id;
    private Integer userId;
    private String name;
    private String md5;
    private Date createTime;
    private String spath;
    private boolean anonymous;
    private String size;
    private String note;

    public UploadFile() {
    }

    public UploadFile(Integer userId, String name, String md5, Date createTime, String spath, boolean anonymous, String size, String note) {
        this.userId = userId;
        this.name = name;
        this.md5 = md5;
        this.createTime = createTime;
        this.spath = spath;
        this.anonymous = anonymous;
        this.size = size;
        this.note = note;
    }


    @Override
    public String toString() {
        return "UploadFile{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", md5='" + md5 + '\'' +
                ", createTime=" + createTime +
                ", spath='" + spath + '\'' +
                ", anonymous=" + anonymous +
                ", size='" + size + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSpath() {
        return spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
