package com.rhc.test;

import com.rhc.bean.Book;
import com.rhc.bean.Page;
import com.rhc.service.BookService;
import com.rhc.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author rhc
 * @date 2021/08/29 17:05:17
 * @Version 1.0
 */
public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"Spring深层解析",new BigDecimal(113.9),"不知名的呆毛",11889,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"Java从入门到放弃",new BigDecimal(113.9),"不知名的呆毛",11889,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookService.queryBooks());
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50));
    }
}