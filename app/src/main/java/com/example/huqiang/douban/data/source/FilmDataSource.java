package com.example.huqiang.douban.data.source;

import com.example.huqiang.douban.data.film.film_details.FilmDetail;
import com.example.huqiang.douban.data.film.film_europe.FilmUsBox;
import com.example.huqiang.douban.data.film.film_hot.FilmLive;
import com.example.huqiang.douban.data.film.film_top250.Root;

import io.reactivex.Observable;

/**
 * Created by HuQiang on 2017/7/26.
 */

public interface FilmDataSource {
    Observable<FilmLive> getFilmLive();

    Observable<FilmDetail> getFilmDetail(String filmId);

    Observable<Root> getTop250(int start, int count);

    Observable<FilmUsBox> getUsBox();
}
