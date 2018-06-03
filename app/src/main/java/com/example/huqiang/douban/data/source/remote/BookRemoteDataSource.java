package com.example.huqiang.douban.data.source.remote;

import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.book.booksearch.BookRoot;
import com.example.huqiang.douban.data.source.BookDataSource;
import com.example.huqiang.douban.retrofit.ApiServer;
import com.example.huqiang.douban.retrofit.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class BookRemoteDataSource implements BookDataSource {
    private static BookRemoteDataSource mInstance;

    public static BookRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new BookRemoteDataSource();
        }
        return mInstance;
    }


    @Override
    public Observable<BookRoot> searchBookByTag(String tag) {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .searchBookByTag(tag)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Books> getBookDetail(String id) {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .getBookDetail(id)
                .subscribeOn(Schedulers.io());
    }
}
