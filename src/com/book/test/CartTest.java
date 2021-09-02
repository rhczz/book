package com.book.test;

import com.book.bean.Cart;
import com.book.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author rhc
 * @date 2021/09/01 19:47:19
 * @Version 1.0
 */
public class CartTest {
    Cart cart = new Cart();

    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(20),new BigDecimal(20)));
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(20),new BigDecimal(20)));

        cart.updateCount(2,3);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(1,"我如何追到富婆",5,new BigDecimal(20.5),new BigDecimal(5*20.5)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(20),new BigDecimal(20)));

        cart.updateCount(2,3);

        System.out.println(cart);

        cart.clear();

        System.out.println(cart);
    }
}