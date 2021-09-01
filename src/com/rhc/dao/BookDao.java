package com.rhc.dao;

import com.rhc.bean.Book;

import java.util.List;

/**
 * @author rhc
 * @date 2021/08/29 16:11:22
 * @Version 1.0
 */
public interface BookDao {
    /**
     * 添加图书方法
     * @param book 添加的图书对象
     * @return 返回-1表示添加失败<br/>返回其他整形表示影响的行数
     */
    int addBook(Book book);

    /**
     * 通过图书id删除图书
     * @param id 删除的图书的id
     * @return 返回-1表示删除失败
     */
    int deleteBookById(Integer id);

    /**
     * 更新图书表的方法
     * @param book 更新之后的图书对象
     * @return 返回-1表示更新失败<br/>其他数表示影响的行数
     */
    int updateBook(Book book);

    /**
     * 通过图书id查找图书
     * @param id 查找的图书的id
     * @return 返回id对应的图书对象<br/>返回null表示没有找到对应id的图书
     */
    Book queryBookById(Integer id);

    /**
     * 查找多本书
     * @return 返回图书对象的集合
     */
    List<Book> queryBooks();

    /**
     * 查询总的记录数
     * @return 返回记录数
     */
    Integer queryForPageTotalCount();

    /**
     * 查询每页的Book对象
     * @param begin 开始的记录
     * @param pageSize 每页显示的Book的个数
     * @return 返回Book集合
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 按照价格查找的总记录数
     * @param min 最小价格
     * @param max 最大价格
     * @return 返回记录总数
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 按价格查找每页的Book对象
     * @param begin 开始的记录
     * @param pageSize 每页显示的BooK对象的个数
     * @param min 最小价格
     * @param max 最大价格
     * @return 返回Book的集合
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
