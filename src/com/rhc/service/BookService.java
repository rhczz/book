package com.rhc.service;

import com.rhc.bean.Book;
import com.rhc.bean.Page;

import java.util.List;

/**
 * @author rhc
 * @date 2021/08/29 16:56:58
 * @Version 1.0
 */
public interface BookService {
    /**
     * 添加图书
     * @param book 添加的图书对象
     */
    void addBook(Book book);

    /**
     * 删除图书
     * @param id 删除的图书的对应id
     */
    void deleteBookById(Integer id);

    /**
     * 更新图书
     * @param book 更新的图书的对象
     */
    void updateBook(Book book);

    /**
     * 查找一条图书记录
     * @param id 查找的图书对应的id
     * @return 返回图书对象
     */
    Book queryBookById(Integer id);

    /**
     * 查找所有图书的集合
     * @return
     */
    List<Book> queryBooks();

    /**
     * 返回分页对象
     * @param pageNo 当前页码
     * @param pageSize 每页显示的内同的大小
     * @return
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * 按价格查找，返回Book对象
     * @param pageNo 页号
     * @param pageSize 每页最大记录数
     * @param min 最小价格
     * @param max 最大价格
     * @return 返回Page对象
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
