package com.book.service.impl;

import com.book.bean.User;
import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.service.UserService;

/**
 * @author rhc
 * @date 2021/08/25 20:36:32
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            //等于null说明没查到username，此username可用
            return false;
        }
        return true;
    }
}
