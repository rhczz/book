package com.book.test;

import com.book.bean.Cart;
import com.book.bean.CartItem;
import com.book.bean.OrderItem;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author rhc
 * @date 2021/09/02 15:31:12
 * @Verder1.0
 */
public class OrderServiceTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(20),new BigDecimal(20)));

        System.out.println("订单号：======"+orderService.createOrder(cart, 1));
    }

    @Test
    public void orderDetails(){
        List<OrderItem> orderItems = orderService.orderDetails("16305728469971");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}