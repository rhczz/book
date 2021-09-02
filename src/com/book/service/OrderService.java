package com.book.service;

import com.book.bean.Cart;
import com.book.bean.Order;
import com.book.bean.OrderItem;

import java.util.List;

/**
 * @author rhc
 * @date 2021/09/02 15:10:42
 * @Version 1.0
 */
public interface OrderService {
    /**
     * 生成订单
     * @return 生成的订单号
     */
    String createOrder(Cart cart,Integer userId);

    /**
     * 查询我的订单
     * @param userId 用户id
     * @return 返回订单数组
     */
    List<Order> myOrders(Integer userId);

    /**
     * 查询订单详情
     * @param orderId 订单id
     * @return 返回订单包括的订单项
     */
    List<OrderItem> orderDetails(String orderId);

    /**
     * 查询所有订单
     * @return 返回所有订单
     */
    List<Order> allOrders();

    /**
     * 将订单修改为已发货
     * @param orderId 订单id
     */
    void sendOrder(String orderId);

    /**
     * 将订单修改为已收货
     * @param orderId 订单id
     */
    void receiveOrder(String orderId);
}
