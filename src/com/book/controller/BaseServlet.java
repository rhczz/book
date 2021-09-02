package com.book.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author rhc
 * @date 2021/08/29 14:47:42
 * @Version 1.0
 */
public abstract class BaseServlet extends HttpServlet {

    /**
     * 处理doGet请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * doPost方法，通过反射机制调用响应的业务方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单隐藏域的value，value的值对应响应的业务方法名
        String action = req.getParameter("action");

        try {
            //通过业务鉴别字符串，获取响应方法的反射对象
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //调用目标方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException(e); //把异常抛给Filter过滤器
        }
    }
}
