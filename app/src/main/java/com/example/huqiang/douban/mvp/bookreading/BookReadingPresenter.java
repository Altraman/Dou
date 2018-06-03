package com.example.huqiang.douban.mvp.bookreading;

import com.example.huqiang.douban.data.book.booksearch.BookRoot;
import com.example.huqiang.douban.data.source.remote.BookRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class BookReadingPresenter implements BookReadingContract.Presenter {
    private CompositeDisposable disposable;
    private BookReadingContract.View view;
    private BookRemoteDataSource dataSource;

    public BookReadingPresenter(BookReadingContract.View view, BookRemoteDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
        this.view.setPresenter(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

    @Override
    public void getBooksByTag(String tag, final boolean isLoadMore) {
        Disposable d = dataSource.searchBookByTag(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<BookRoot>() {
                    @Override
                    public void onNext(BookRoot value) {
                        view.showBooks(value, isLoadMore);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showNetError();
                        view.setLoadingIndicator(false);
                    }

                    @Override
                    public void onComplete() {
                        view.setLoadingIndicator(false);
                    }
                });
        disposable.add(d);
    }
}
