package com.smart.domain;

import java.util.Date;

public class LoginLog {

    private int loginLogId;
    private int userId;
    private String ip;
    private Date loginDatetime;

    @Override
    public String toString() {
        return "LoginLog{" +
                "loginLogId=" + loginLogId +
                ", userId=" + userId +
                ", ip='" + ip + '\'' +
                ", loginDatetime=" + loginDatetime +
                '}';
    }

    public LoginLog() {
    }

    public LoginLog(int userId, String ip, Date loginDatetime) {
        this.userId = userId;
        this.ip = ip;
        this.loginDatetime = loginDatetime;
    }

    public LoginLog(int loginLogId, int userId, String ip, Date loginDatetime) {
        this.loginLogId = loginLogId;
        this.userId = userId;
        this.ip = ip;
        this.loginDatetime = loginDatetime;
    }

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }
}
