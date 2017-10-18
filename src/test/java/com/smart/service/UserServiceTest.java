package com.smart.service;

import com.smart.domain.User;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

@ContextConfiguration("classpath*:/smart-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void testMathUser() {
        boolean b1 = this.userService.hasMatchUser("admin", "123456");
        boolean b2 = this.userService.hasMatchUser("admin", "111111");
        Assert.assertTrue(b1);
        Assert.assertTrue(!b2);
    }

    @Test
    public void findUserByUserName() {
        User user = this.userService.findUserByUserName("admin");
        System.out.println(user.toString());
        Assert.assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void testAddLoginLog(){
        User user = this.userService.findUserByUserName("admin");

        user.setLastIp("192.168.1.102");
        user.setLastVisit(new Date());

        this.userService.loginSuccess(user);
    }

}
