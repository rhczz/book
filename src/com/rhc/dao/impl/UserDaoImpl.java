package com.rhc.dao.impl;

import com.rhc.bean.User;
import com.rhc.dao.UserDao;

/**
 * @author rhc
 * @date 2021/08/25 20:02:56
 * @Version 1.0
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "SELECT * FROM t_user WHERE username = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM t_user WHERE username = ? AND password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
