package com.qmkl.entity;
import java.util.Date;

public class FileDetail {
    private String name;
    private String size;
    private Date createAt;
    private Date updateAt;
    private String md5;
    private String nick;
    private String uid;
    private Integer id;
    private Integer likeNum;
    private Integer dislikeNum;

    @Override
    public String toString() {
        return "FileDetail{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", md5='" + md5 + '\'' +
                ", nick='" + nick + '\'' +
                ", uid='" + uid + '\'' +
                ", id=" + id +
                ", likeNum=" + likeNum +
                ", dislikeNum=" + dislikeNum +
                '}';
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getDislikeNum() {
        return dislikeNum;
    }

    public void setDislikeNum(Integer dislikeNum) {
        this.dislikeNum = dislikeNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }




}
