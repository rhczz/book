package com.book.dao;

import com.book.bean.Order;

import java.util.List;

/**
 * @author rhc
 * @date 2021/09/02 14:46:27
 * @Version 1.0
 */
public interface OrderDao {

    /**
     * 向数据库中保存订单
     * @param order 订单
     * @return 如果返回是-1表示操作失败<br/>如果返回正数，表示影响的行数
     */
    int saveOrder(Order order);


    /**
     * 返回订单数组
     * @param userId 用户id
     * @return
     */
    List<Order> queryMyOrders(Integer userId);

    /**
     * 管理员查询所有订单
     * @return
     */
    List<Order> queryAllOrders();

    /**
     * 修改订单状态
     * @param orderId 订单id
     * @return 返回-1表示修改失败，返回正数表示影响的行数
     */
    int changeOrderStatus(Integer status,String orderId);
}
