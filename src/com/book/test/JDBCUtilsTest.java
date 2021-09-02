package com.book.test;

import com.book.utils.JDBCUtils;
import org.junit.Test;

/**
 * 测试JDBCUtils
 * @author rhc
 * @date 2021/08/25 18:45:47
 * @Version 1.0
 */
public class JDBCUtilsTest {
    @Test
    public void testJDBCUtils(){


        System.out.println(JDBCUtils.getConnection());



    }
}
