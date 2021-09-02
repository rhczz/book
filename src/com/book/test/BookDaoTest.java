package com.book.test;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author rhc
 * @date 2021/08/29 16:42:09
 * @Version 1.0
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"我为什么这么帅",new BigDecimal(99.9),"任红昌",102400,0,null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"屌丝怎么让富婆爱上你",new BigDecimal(99.9),"任红昌",102400,0,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount(){
        System.out.println( bookDao.queryForPageTotalCount() );
    }

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(4, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItemsByPrice(){
        for (Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50)) {
            System.out.println(book);
        }
    }
}