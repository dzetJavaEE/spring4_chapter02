package com.smart.web;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 1 标注为一个Spring MVC的Controller
 */
@Controller
public class LoginController implements ILoginController{

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 2 负责处理 /index.html的请求
     * @return
     */
    @RequestMapping(value = "/index.html")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
        boolean isValidUser = this.userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());

        if (!isValidUser){
            return new ModelAndView("login","error","用户名或密码错误");
        }else {
            User user = this.userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());

            this.userService.loginSuccess(user);

            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");

        }

    }

}
