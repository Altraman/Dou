package com.example.huqiang.douban.mvp.filmdetail;

import com.example.huqiang.douban.data.film.film_details.FilmDetail;
import com.example.huqiang.douban.mvp.BasePresenter;
import com.example.huqiang.douban.mvp.BaseView;

/**
 * Created by HuQiang on 2017/7/27.
 */

public interface FilmDetailContract {
    interface Presenter extends BasePresenter {
        void getFilmDetail(String id);
    }

    interface View extends BaseView<Presenter> {
        void showFilmDetail(FilmDetail filmDetail);

        void showNetError();
    }
}
