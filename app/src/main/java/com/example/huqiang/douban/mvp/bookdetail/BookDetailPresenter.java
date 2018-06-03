package com.example.huqiang.douban.mvp.bookdetail;

import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.source.remote.BookRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class BookDetailPresenter implements BookDetailContract.Presenter {
    private CompositeDisposable disposable;
    private String bookId;
    private BookRemoteDataSource dataSource;
    private BookDetailContract.View view;

    public BookDetailPresenter(String bookId, BookRemoteDataSource dataSource, BookDetailContract.View view) {
        this.bookId = bookId;
        this.dataSource = dataSource;
        this.view = view;
        this.view.setPresenter(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void onSubscribe() {
        getBookDetail(bookId);
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

    @Override
    public void getBookDetail(String id) {
        Disposable d = dataSource.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Books>() {
                    @Override
                    public void onNext(Books value) {
                        view.showBookDetail(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showNetError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        disposable.add(d);
    }
}
