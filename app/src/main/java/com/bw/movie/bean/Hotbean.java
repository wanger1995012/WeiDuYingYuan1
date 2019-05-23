package com.bw.movie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/23 8:37
 * @Description：描述信息
 */

@Entity
public class Hotbean {
    private int followMovie;
    private int id;
    private String imageUrl;
    private String name;
    private int rank;
    private String summary;
    @Generated(hash = 1343743866)
    public Hotbean(int followMovie, int id, String imageUrl, String name, int rank,
            String summary) {
        this.followMovie = followMovie;
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.rank = rank;
        this.summary = summary;
    }
    @Generated(hash = 1560814118)
    public Hotbean() {
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
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
