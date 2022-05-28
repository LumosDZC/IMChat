package com.hx.imchat.user;

import android.graphics.Bitmap;

/**
 * 捕获从 LoginRepository 检索的登录用户的用户信息的数据类
 */
public class User {

    private String name;
    private String phoneNumber;
    private int age;
    private Boolean isMan;
    private String occupation;
    private String IP;
    private String creationTime;
    private int state;
    private Bitmap avatar;
    public User(String name, String phoneNumber, int age, Boolean isMan, String occupation) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.isMan = isMan;
        this.occupation = occupation;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getMan() {
        return isMan;
    }

    public void setMan(Boolean man) {
        isMan = man;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", isMan=" + isMan +
                ", occupation='" + occupation + '\'' +
                ", IP='" + IP + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", state=" + state +
                '}';
    }
}
