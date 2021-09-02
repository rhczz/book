package com.book.controller;

import com.book.bean.Cart;
import com.book.bean.Order;
import com.book.bean.OrderItem;
import com.book.bean.User;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;
import com.book.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rhc
 * @date 2021/09/02 15:36:00
 * @Version 1.0
 */
@WebServlet("/order")
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User loginUser = (User) req.getSession().getAttribute("user");
        Integer userId = loginUser.getId();

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        String orderId = orderService.createOrder(cart, userId);
        
        //将订单号保存在request域中
        //req.setAttribute("orderId",orderId);
        //请求转发到/pages/cart/checkout.jsp
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 返回我的所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户id
        User user = (User) req.getSession().getAttribute("user");
        Integer id = user.getId();

        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        //调用orderService.myOrders()返回用户的所有订单
        List<Order> orders = orderService.myOrders(id);
        //将订单存到request域中
        req.setAttribute("orders",orders);
        //请求转发到/pages/order/order.jsp
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * 返回订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单编号
        String orderId = req.getParameter("id");
        //调用orderService.orderDetails()方法查找订单详情
        List<OrderItem> orderItems = orderService.orderDetails(orderId);
        //存入request域中
        req.setAttribute("orderDetails",orderItems);
        //页面重定向
        req.getRequestDispatcher("/pages/order/order_details.jsp").forward(req,resp);
    }

    /**
     * 查询所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用orderService.allOrders()获取所有订单
        List<Order> orders = orderService.allOrders();
        //存入request域中
        req.setAttribute("orders",orders);
        //页面重定向
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     * 确认发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单id
        String orderId = req.getParameter("id");
        //调用orderService.sendOrder()修改订单状态
        orderService.sendOrder(orderId);
        //页面重定向
        resp.sendRedirect(req.getContextPath()+"/order?action=allOrders");
    }

    /**
     * 用户确认收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单id
        String orderId = req.getParameter("id");
        //调用orderService.receiveOrder()方法确认收货
        orderService.receiveOrder(orderId);
        //页面重定向
        resp.sendRedirect(req.getContextPath()+"/order?action=queryMyOrders");
    }
}
