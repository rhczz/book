package com.book.test;

import com.book.bean.Order;
import com.book.dao.OrderDao;
import com.book.dao.impl.OrderDaoImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author rhc
 * @date 2021/09/02 15:00:13
 * @Version 1.0
 */
public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("A1100",new Date(),new BigDecimal(100),1,1));
    }

    @Test
    public void queryAllOrders(){
        List<Order> orders = orderDao.queryAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus(){
        orderDao.changeOrderStatus(1,"16305697273416");
    }
}