package com.rhc.utils;

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
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接，返回数据库连接池
     * @param connection
     */
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
