package com.example.huqiang.douban.mvp.top250film;

import com.example.huqiang.douban.data.film.film_top250.Root;
import com.example.huqiang.douban.data.source.remote.FilmRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/27.
 */

public class FilmTopPresenter implements FilmTopContract.Presenter {
    private CompositeDisposable disposable;
    private FilmRemoteDataSource dataSource;
    private FilmTopContract.View view;

    public FilmTopPresenter(FilmRemoteDataSource dataSource, FilmTopContract.View view) {
        this.dataSource = dataSource;
        this.view = view;
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
    public void getTop250(int start, int count, final boolean isLoadMore) {
        Disposable d = dataSource.getTop250(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Root>() {
                    @Override
                    public void onNext(Root value) {
                        view.showTop250(value, isLoadMore);
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
