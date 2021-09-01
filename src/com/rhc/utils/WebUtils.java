package com.rhc.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author rhc
 * @date 2021/08/29 15:01:18
 * @Version 1.0
 */
public class WebUtils {
    /**
     * 把Map中的值注入到JavaBean对应的属性中
     * @param value 请求参数HttpServletRequest对象的Map集合
     * @param bean JavaBean对象
     */
    public static <T> T copyParam2Bean(Map value, T bean) {
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串类型的数据转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
