package com.qmkl.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateInfo {


    // 是否有新版本
    private boolean hasUpdate;

    // 是否静默下载：有新版本时不提示直接下载
   // @JsonProperty("isSilent")
    private boolean isSilent;

    // 是否强制安装：不安装无法使用app
    //@JsonProperty("isForce")
    private boolean isForce;

    // 是否下载完成后自动安装
   //@JsonProperty("isAutoInstall")
    private boolean isAutoInstall;

    // 是否可忽略该版本
   // @JsonProperty("isIgnorable")
    private boolean isIgnorable;

    private int versionCode;
    private String versionName;
    private String updateContent;
    private String url;
    private String md5;
    private long size;


    @Override
    public String toString() {
        return "UpdateInfo{" +
                "hasUpdate=" + hasUpdate +
                ", isSilent=" + isSilent +
                ", isForce=" + isForce +
                ", isAutoInstall=" + isAutoInstall +
                ", isIgnorable=" + isIgnorable +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", updateContent='" + updateContent + '\'' +
                ", url='" + url + '\'' +
                ", md5='" + md5 + '\'' +
                ", size=" + size +
                '}';
    }

    public boolean isHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public boolean getIsSilent() {
        return isSilent;
    }

    public void setIsSilent(boolean silent) {
        isSilent = silent;
    }

    public boolean getIsForce() {
        return isForce;
    }

    public void setIsForce(boolean force) {
        isForce = force;
    }

    public boolean getIsAutoInstall() {
        return isAutoInstall;
    }

    public void setIsAutoInstall(boolean autoInstall) {
        isAutoInstall = autoInstall;
    }

    public boolean getIsIgnorable() {
        return isIgnorable;
    }

    public void setIsIgnorable(boolean ignorable) {
        isIgnorable = ignorable;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
