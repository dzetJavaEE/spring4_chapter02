package com.smart.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private int userId;
    private String userName;
    private int credits;
    private String password;
    private Date lastVisit;
    private String lastIp;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", credits=" + credits +
                ", password='" + password + '\'' +
                ", lastVisit=" + lastVisit +
                ", lastIp='" + lastIp + '\'' +
                '}';
    }

    public User() {
    }

    public User(String userName, int credits, String password, Date lastVisit, String lastIp) {
        this.userName = userName;
        this.credits = credits;
        this.password = password;
        this.lastVisit = lastVisit;
        this.lastIp = lastIp;
    }

    public User(int userId, String userName, int credits, String password, Date lastVisit, String lastIp) {
        this.userId = userId;
        this.userName = userName;
        this.credits = credits;
        this.password = password;
        this.lastVisit = lastVisit;
        this.lastIp = lastIp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }
}
