package com.book.dao;

import com.book.bean.User;

/**
 * @author rhc
 * @date 2021/08/25 19:57:49
 * @Version 1.0
 */
public interface UserDao {
    /**
     * 根据用户名返回用户信息
     * @param username
     * @return 如果返回null说明没有这个用户，反之则返回用户对象
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null说明没有这个用户，反之则返回用户对象
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败<br/>其他是sql语句影响的行数
     */
    public int saveUser(User user);


}
