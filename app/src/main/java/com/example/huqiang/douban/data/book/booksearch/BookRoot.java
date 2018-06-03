package com.example.huqiang.douban.data.book.booksearch;

import com.example.huqiang.douban.data.book.bookdetails.Books;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class BookRoot {

    /**
     * count : 15
     * start : 0
     * total : 15
     */

    private int count;
    private int start;
    private int total;
    private List<Books> books;

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
