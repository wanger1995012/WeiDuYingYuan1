package com.bw.movie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class ZhuceBean {
    public long birthday;
    public String headPic;
    public long lastLoginTime;
    public String nickName;
    public String phone;
    public int sex;

    public ZhuceBean(long birthday, String headPic, long lastLoginTime, String nickName, String phone, int sex) {
        this.birthday = birthday;
        this.headPic = headPic;
        this.lastLoginTime = lastLoginTime;
        this.nickName = nickName;
        this.phone = phone;
        this.sex = sex;
    }

    public ZhuceBean() {
    }

    public long getBirthday() {
        return this.birthday;
    }
    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }
    public String getHeadPic() {
        return this.headPic;
    }
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
    public long getLastLoginTime() {
        return this.lastLoginTime;
    }
    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
}
