package com.rhc.test;

import com.rhc.bean.User;
import com.rhc.dao.UserDao;
import com.rhc.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author rhc
 * @date 2021/08/25 20:18:11
 * @Version 1.0
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已经存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin","admin") == null){
            System.out.println("用户名或密码错误！");
        }else {
            System.out.println("登陆成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"rhc11","123456","1111@qq.com")));
    }
}