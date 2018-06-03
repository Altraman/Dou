package com.example.huqiang.douban.mvp.filmdetail;

import android.util.Log;

import com.example.huqiang.douban.data.film.film_details.FilmDetail;
import com.example.huqiang.douban.data.source.remote.FilmRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/27.
 */

public class FilmDetailPresenter implements FilmDetailContract.Presenter {
    private String filmId;
    private FilmRemoteDataSource filmRemoteDataSource;
    private CompositeDisposable disposable;
    private FilmDetailContract.View view;

    public FilmDetailPresenter(String filmId, FilmRemoteDataSource filmRemoteDataSource, FilmDetailContract.View view) {
        this.filmId = filmId;
        this.filmRemoteDataSource = filmRemoteDataSource;
        this.view = view;
        this.view.setPresenter(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void onSubscribe() {
        getFilmDetail(filmId);
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

    @Override
    public void getFilmDetail(String id) {
        Log.d("id", id);
        Disposable d = filmRemoteDataSource.getFilmDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<FilmDetail>() {
                    @Override
                    public void onNext(FilmDetail value) {
                        view.showFilmDetail(value);
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
