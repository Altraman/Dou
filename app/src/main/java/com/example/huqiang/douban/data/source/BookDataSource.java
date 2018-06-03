package com.example.huqiang.douban.data.source;

import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.book.booksearch.BookRoot;

import io.reactivex.Observable;

/**
 * Created by HuQiang on 2017/7/26.
 */

public interface BookDataSource {
    Observable<BookRoot> searchBookByTag(String tag);

    Observable<Books> getBookDetail(String id);
}
