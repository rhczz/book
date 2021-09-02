package com.book.controller;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rhc
 * @date 2021/08/30 19:39:06
 * @Version 1.0
 */
@WebServlet("/client/book")
public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2. 调用bookService.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置分页请求的url
        page.setUrl("client/book?action=page");

        //3. 保存page到request域中
        req.setAttribute("page",page);
        //4. 请求准发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        //2. 调用bookService.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/book?action=pageByPrice");
        //如果有min的请求参数，追加到分页条的请求参数中
        if (req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        //如果有max的请求参数，追加到分页条的请求参数中
        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        //设置分页请求的url
        page.setUrl(sb.toString());

        //3. 保存page到request域中
        req.setAttribute("page",page);
        //4. 请求准发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
