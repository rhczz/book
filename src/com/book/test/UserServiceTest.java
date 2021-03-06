package com.book.test;

import com.book.bean.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author rhc
 * @date 2021/08/25 20:47:47
 * @Version 1.0
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"gxx","6666","gxx@126.com"));
        userService.registUser(new User(null,"llx","llxaaa","llx@foxmail.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "llx", "llxaaa", null)));
        System.out.println(userService.login(new User(null, "llx", "llx", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("admin")) {
            System.out.println("用户名已经存在！");
        }else {
            System.out.println("用户名可用");
        }
    }
}