package com.book.controller;

import com.book.bean.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author rhc
 * @date 2021/08/29 13:52:08
 * @Version 1.0
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 处理Ajax请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求的参数
        String username = req.getParameter("username");
        //2. 调用userservice.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        //3. 把返回的结果封装为Map集合
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        //4. 使用gson将Map准换成json
        Gson gson = new Gson();
        String toJson = gson.toJson(resultMap);

        //5. 通过响应的字符输出流，将json输出
        resp.getWriter().write(toJson);
    }

    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2. 处理登录
        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser == null){
            //登录失败
            //把错误信息和回显的表单信息保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            //回显消息
            req.setAttribute("username",username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //保存用户登录后的信息到Session域中
            req.getSession().setAttribute("user",loginUser);
            //登陆成功
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*验证码防止表单重复提交*/
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1. 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //调用WebUtils工具类，将请求参数注入到User对象中，返回注入之后的user对象
        User user = WebUtils.copyParam2Bean(req.getParameterMap(),new User());


        //2. 检查验证码是否正确
        if (token !=null && token.equalsIgnoreCase(code)){
            //3. 检查用户名是否正确
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已经被注册");

                //把回显信息保存在request域中
                req.setAttribute("msg","用户名已存在！！！");
                //回显用户名
                req.setAttribute("username",username);
                //回显邮箱
                req.setAttribute("email",email);

                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用
                userService.registUser(new User(null,username,password,email));
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            System.out.println("验证码"+ code +"错误");

            //把回显信息保存在request域中
            req.setAttribute("msg","验证码错误！！！");
            //回显用户名
            req.setAttribute("username",username);
            //回显邮箱
            req.setAttribute("email",email);

            //跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注销功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        HttpSession session = req.getSession();
        session.invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }
}
