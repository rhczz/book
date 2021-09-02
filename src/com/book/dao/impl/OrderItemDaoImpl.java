package com.book.dao.impl;

import com.book.bean.OrderItem;
import com.book.dao.OrderItemDao;

import java.util.List;

/**
 * @author rhc
 * @date 2021/09/02 14:55:20
 * @Version 1.0
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderDetailByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }


}
