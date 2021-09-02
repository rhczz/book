package com.book.filter;

import com.book.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 在过滤器中对所有方法进行try-catch操作，实现事务的的一致性
 * @author rhc
 * @date 2021/09/02 22:11:26
 * @Version 1.0
 */
@WebFilter("/*")
public class TransationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);

            JDBCUtils.commitAndClose(); //如果没有异常，提交事务，并关闭连接
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();  //如果有异常，回滚事务并关闭连接
            e.printStackTrace();

            //将异常抛给tomcat服务器，用于错误页面跳转
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
