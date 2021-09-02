package com.book.bean;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author rhc
 * @date 2021/09/01 19:22:48
 * @Version 1.0
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> cartItems = new LinkedHashMap<Integer,CartItem>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = cartItems.get(cartItem.getId());

        if (item == null) {
            //之前没添加过
            cartItems.put(cartItem.getId(),cartItem);
        }else {
            //之前添加过
            //数量累加
            item.setCount(item.getCount() + 1);
            //更新价格
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除产品项
     * @param id 需要删除的产品的id
     */
    public void deleteItem(Integer id){
        cartItems.remove(id);
    }

    /**
     * 修改产品数量
     * @param id 需要修改的产品的id
     * @param count 修改后的产品数量
     */
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = cartItems.get(id);

        if (cartItem != null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    /**
     * 清空购物车
     */
    public void clear(){
        cartItems.clear();
    }


    public Integer getTotalCount() {

        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : cartItems.entrySet()) {
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : cartItems.values()) {

            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer,CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer,CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", cartItems=" + cartItems +
                '}';
    }
}
