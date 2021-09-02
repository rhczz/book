package com.book.dao;

import com.book.bean.Order;
import com.book.bean.OrderItem;

import java.util.List;

/**
 * @author rhc
 * @date 2021/09/02 14:47:24
 * @Version 1.0
 */
public interface OrderItemDao {

    /**
     * 向数据库中保存订单项
     * @param orderItem 订单项
     * @return 如果返回是-1表示操作失败<br/>如果返回正数，表示影响的行数
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 返回订单明细
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderDetailByOrderId(String orderId);

}
