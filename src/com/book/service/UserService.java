package com.book.service;

import com.book.bean.User;

/**
 * @author rhc
 * @date 2021/08/25 20:32:15
 * @Version 1.0
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null表示登录失败，返回有值表示登陆成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已经存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
