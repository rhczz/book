package com.book.service.impl;

import com.book.bean.*;
import com.book.dao.BookDao;
import com.book.dao.OrderDao;
import com.book.dao.OrderItemDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.dao.impl.OrderDaoImpl;
import com.book.dao.impl.OrderItemDaoImpl;
import com.book.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author rhc
 * @date 2021/09/02 15:12:26
 * @Version 1.0
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号====唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);


        //遍历购物车中的每一个商品转化为订单的订单项
        for (Map.Entry<Integer, CartItem> entry : cart.getCartItems().entrySet()) {
            //获取购物车中的商品项
            CartItem cartItem = entry.getValue();
            //创建订单项对象，将商品项的内容放到订单项中
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项
            orderItemDao.saveOrderItem(orderItem);


            /*更新库存和销量*/
            //查找图书信息
            Book book = bookDao.queryBookById(cartItem.getId());
            //修改销量
            book.setSales(book.getSales() + cartItem.getCount());
            //修改库存
            book.setStock(book.getStock() - cartItem.getCount());
            //保存修改后的图书信息
            bookDao.updateBook(book);
        }

        //清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> myOrders(Integer userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<OrderItem> orderDetails(String orderId) {
        return orderItemDao.queryOrderDetailByOrderId(orderId);
    }

    @Override
    public List<Order> allOrders() {
        return orderDao.queryAllOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(1,orderId);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(2,orderId);
    }
}
