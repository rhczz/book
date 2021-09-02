package com.book.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author rhc
 * @date 2021/08/29 14:14:04
 * @Version 1.0
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是login()方法调用了");
    }
    public void regist(){
        System.out.println("regist()方法调用了");
    }
    public void updateUser(){
        System.out.println("updateUser()方法调用了");
    }
    public void updateUserPassword(){
        System.out.println("updateUserPassword()方法调用了");
    }
    public void unsubscribe(){
        System.out.println("unsubscribe()方法调用了");
    }

    public static void main(String[] args) {
        String action = "unsubscribe";
        try {
            //获取action业务鉴别字符串，获取相应业务方法反射的对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);

            //调用目标业务方法
            method.invoke(new UserServletTest());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
