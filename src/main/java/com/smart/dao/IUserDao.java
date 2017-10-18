package com.smart.dao;

import com.smart.domain.User;

public interface IUserDao {
    int getMatchCount(String userName, String password);

    User findUserByUserName(String userName);

    void updateLoginInfo(User user);
}
