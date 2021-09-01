package com.rhc.test;

import com.rhc.utils.JDBCUtils;
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
