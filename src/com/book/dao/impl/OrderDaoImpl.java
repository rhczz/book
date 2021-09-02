package com.book.dao.impl;

import com.book.bean.Order;
import com.book.dao.OrderDao;

import java.util.List;

/**
 * @author rhc
 * @date 2021/09/02 14:48:14
 * @Version 1.0
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public int changeOrderStatus(Integer status,String orderId) {
        String sql = "update t_order set status = ? where order_id = ?";
        return update(sql,status,orderId);
    }
}
