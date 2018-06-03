package com.example.huqiang.douban.mvp.hotfilm;

import com.example.huqiang.douban.data.film.Subject;
import com.example.huqiang.douban.mvp.BasePresenter;
import com.example.huqiang.douban.mvp.BaseView;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/26.
 */

public interface FilmHotContract {
    interface Presenter extends BasePresenter {
        void getFilmLive();
    }

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showFilmHot(List<Subject> subject);

        void showError();
    }
}
