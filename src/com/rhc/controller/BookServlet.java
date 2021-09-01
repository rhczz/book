package com.rhc.controller;

import com.rhc.bean.Book;
import com.rhc.bean.Page;
import com.rhc.service.BookService;
import com.rhc.service.impl.BookServiceImpl;
import com.rhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rhc
 * @date 2021/08/29 17:14:18
 * @Version 1.0
 */
@WebServlet("/manager/book")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加图书的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1. 获取请求参数，封装成Book对象
        Book book = WebUtils.copyParam2Bean(req.getParameterMap(),new Book());
        //2. 调用BookService.addBook()方法保存图书
        bookService.addBook(book);
        //3. 跳转到图书列表
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=page&pageNo=" + pageNo);
    }

    /**
     * 删除图书的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2. 调用bookService.deleteBookById()
        bookService.deleteBookById(id);
        //3. 页面重定向到图书管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 点击修改图书按钮访问此方法，将对饮的图书对象带回到修改图书表单中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求的参数的图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2. 调用bookService.queryBookById()查询图书记录
        Book book = bookService.queryBookById(id);
        //3. 保存图书到request域中
        req.setAttribute("book",book);
        //4. 请求转发到 /pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 将修改后的图书对象保存到数据库中<br/>重定向到图书管理列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数，封装成Book对象
        Book book = WebUtils.copyParam2Bean(req.getParameterMap(),new Book());
        //2. 调用bookService.updateBook(Book)方法将修改后的图书保存到数据库
        bookService.updateBook(book);
        //3. 重定向到图书管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 查找全部图书记录<br/>请求转发到/pages/manager/book_manager.jsp页面
     * @param req HttpServletRequest请求对象
     * @param resp HtppServletResponse响应对象
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2. 把全部图书保存到request域中
        req.setAttribute("books",books);
        //3. 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

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
        page.setUrl("manager/book?action=page");

        //3. 保存page到request域中
        req.setAttribute("page",page);
        //4. 请求准发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
