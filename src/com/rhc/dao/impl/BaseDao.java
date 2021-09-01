package com.rhc.dao.impl;

import com.rhc.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author rhc
 * @date 2021/08/25 19:30:03
 * @Version 1.0
 */
public abstract class BaseDao {
    //使用DBUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行Insert\Update\delete语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return 如果返回-1说明执行失败<br/>返回其他表示影响的行数
     */
    public int update(String sql,Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询返回一个JavaBean的sql语句
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return null;
    }

    /**
     * 返回多个JavaBean的方法
     * @param type 返回的对象类型
     * @param sql 执行的SQL语句
     * @param args SQL对应的参数
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数
     * @return
     */
    public Object queryForSingleValue(String sql,Object ...args) {
        Connection conn = JDBCUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return null;
    }
}
