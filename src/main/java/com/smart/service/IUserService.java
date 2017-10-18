package com.smart.service;

import com.smart.domain.User;

public interface IUserService {

    boolean hasMatchUser(String userName, String password);

    User findUserByUserName(String userName);

    void loginSuccess(User user);

}
