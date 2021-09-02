package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author rhc
 * @date 2021/08/25 18:27:52
 * @Version 1.0
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static{
        try {
            Properties properties = new Properties();
            //读取jdbc.properties配置文件
            InputStream asStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(asStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection() {
        Connection conn = conns.get();

        if (conn == null) {
            try {
                conn = dataSource.getConnection(); //从数据库连接池中获取连接
                conns.set(conn);  //保存到ThreadLocal中，供后边的jdbc操作使用
                conn.setAutoCommit(false); //设置为手动提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) { //不过！=null说明之前使用过连接，操作过数据库

            try {
                connection.commit(); //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close(); //关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) { //不过！=null说明之前使用过连接，操作过数据库

            try {
                connection.rollback(); //事务回滚
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close(); //关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

}
