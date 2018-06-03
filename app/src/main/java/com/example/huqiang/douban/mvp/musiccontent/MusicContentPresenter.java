package com.example.huqiang.douban.mvp.musiccontent;

import com.example.huqiang.douban.data.book.booksearch.BookRoot;
import com.example.huqiang.douban.data.music.musicsearch.MusicRoot;
import com.example.huqiang.douban.data.source.remote.BookRemoteDataSource;
import com.example.huqiang.douban.data.source.remote.MusicRemoteDataSource;
import com.example.huqiang.douban.mvp.bookreading.BookReadingContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class MusicContentPresenter implements MusicContentContract.Presenter {
    private CompositeDisposable disposable;
    private MusicContentContract.View view;
    private MusicRemoteDataSource dataSource;

    public MusicContentPresenter(MusicContentContract.View view, MusicRemoteDataSource dataSource) {
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
    public void getMusicsByTag(String tag, final boolean isLoadMore, final int refreshType) {
        Disposable d = dataSource.searchMusicByTag(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MusicRoot>() {
                    @Override
                    public void onNext(MusicRoot value) {
                        view.showMusics(value, isLoadMore, refreshType);
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
