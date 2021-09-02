package com.book.test;

import com.book.bean.OrderItem;
import com.book.dao.OrderItemDao;
import com.book.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author rhc
 * @date 2021/09/02 15:04:30
 * @Version 1.0
 */
public class OrderItemDaoTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"最后的晚餐",3,new BigDecimal(44.5),new BigDecimal(44.5 * 3),"A1100"));
        orderItemDao.saveOrderItem(new OrderItem(null,"从你的全世界路过",1,new BigDecimal(60),new BigDecimal(60),"A1100"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Linux教程",1,new BigDecimal(88),new BigDecimal(88),"A1100"));
    }

    @Test
    public void queryOrderDetailByOrderId(){
        List<OrderItem> orderItems = orderItemDao.queryOrderDetailByOrderId("16305697273416");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }


}