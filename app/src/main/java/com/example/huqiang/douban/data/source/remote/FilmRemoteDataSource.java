package com.example.huqiang.douban.data.source.remote;

import com.example.huqiang.douban.data.film.film_details.FilmDetail;
import com.example.huqiang.douban.data.film.film_europe.FilmUsBox;
import com.example.huqiang.douban.data.film.film_hot.FilmLive;
import com.example.huqiang.douban.data.film.film_top250.Root;
import com.example.huqiang.douban.data.source.FilmDataSource;
import com.example.huqiang.douban.retrofit.ApiServer;
import com.example.huqiang.douban.retrofit.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/26.
 */

public class FilmRemoteDataSource implements FilmDataSource {
    private static FilmRemoteDataSource mInstance;

    public static FilmRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new FilmRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public Observable<FilmLive> getFilmLive() {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .getLiveFilms()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<FilmDetail> getFilmDetail(String filmId) {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .getFilmDetail(filmId)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Root> getTop250(int start, int count) {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .getTop250(start, count)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<FilmUsBox> getUsBox() {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .getUsBox()
                .subscribeOn(Schedulers.io());
    }
}
