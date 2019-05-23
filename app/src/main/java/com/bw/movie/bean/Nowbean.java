package com.bw.movie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/23 8:38
 * @Description：描述信息
 */



@Entity
public class Nowbean {
    private int followMovie;
    private int id;
    private String imageUrl;
    private String name;
    private int rank;
    private long releaseTime;
    private String releaseTimeShow;
    private String summary;
    @Generated(hash = 2057840153)
    public Nowbean(int followMovie, int id, String imageUrl, String name, int rank,
            long releaseTime, String releaseTimeShow, String summary) {
        this.followMovie = followMovie;
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.rank = rank;
        this.releaseTime = releaseTime;
        this.releaseTimeShow = releaseTimeShow;
        this.summary = summary;
    }
    @Generated(hash = 1555416680)
    public Nowbean() {
    }
    public int getFollowMovie() {
        return this.followMovie;
    }
    public void setFollowMovie(int followMovie) {
        this.followMovie = followMovie;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRank() {
        return this.rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public long getReleaseTime() {
        return this.releaseTime;
    }
    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }
    public String getReleaseTimeShow() {
        return this.releaseTimeShow;
    }
    public void setReleaseTimeShow(String releaseTimeShow) {
        this.releaseTimeShow = releaseTimeShow;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
