package com.example.huqiang.douban.mvp.hotfilm;

import com.example.huqiang.douban.data.film.film_hot.FilmLive;
import com.example.huqiang.douban.data.source.remote.FilmRemoteDataSource;
import com.example.huqiang.douban.mvp.hotfilm.FilmHotContract.Presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/26.
 */

public class FilmHotPresenter implements Presenter {
    private FilmRemoteDataSource dataSource;
    private CompositeDisposable disposable;
    private FilmHotContract.View view;

    public FilmHotPresenter(FilmRemoteDataSource dataSource, FilmHotContract.View view) {
        this.dataSource = dataSource;
        this.view = view;
        this.view.setPresenter(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void onSubscribe() {
        getFilmLive();
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

    @Override
    public void getFilmLive() {
        Disposable d = dataSource.getFilmLive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<FilmLive>() {
                    @Override
                    public void onNext(FilmLive value) {
                        view.showFilmHot(value.getSubjects());
                        view.setLoadingIndicator(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError();
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
