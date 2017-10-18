package com.smart.web;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface ILoginController {
    String loginPage();

    ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand);
}
