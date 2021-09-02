package com.book.controller;

import com.book.bean.Book;
import com.book.bean.Cart;
import com.book.bean.CartItem;
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
 * @date 2021/09/01 20:20:58
 * @Version 1.0
 */
@WebServlet("/cart")
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * 点击按钮将图书添加到购物车中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("加入购物车！！");
        //1. 获取请求参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2. 调用bookService.queryBookById(id)获取图书信息
        Book book = bookService.queryBookById(id);
        //3. 把图书信息转换成CartItem商品项
        CartItem cartItem = new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        //4. 调用cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        //5. 重定向到原来商品所在的地址页面
        //获取请求头信息:请求发起时地址栏的地址
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);

        //把最后一个添加的书存在session域中，给首页回显
        req.getSession().setAttribute("lastName",cartItem.getName());
    }

    /**
     * 删除购物车中的商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2. 获取session中的购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {

            //3. 调用deleteItem()方法
            cart.deleteItem(id);
            //重定向
            //获取请求头信息:请求发起时地址栏的地址
            String referer = req.getHeader("Referer");
            resp.sendRedirect(referer);
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取session中的cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {

            //2. 调用clear方法
            cart.clear();
            //3. 页面重定向
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改商品项数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),0);
        //2. 获取session中的cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id,count);
            //3. 页面重定向
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
