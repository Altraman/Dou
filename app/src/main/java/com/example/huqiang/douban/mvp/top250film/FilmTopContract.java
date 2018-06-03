package com.example.huqiang.douban.mvp.top250film;

import com.example.huqiang.douban.data.film.film_top250.Root;
import com.example.huqiang.douban.mvp.BasePresenter;
import com.example.huqiang.douban.mvp.BaseView;

/**
 * Created by HuQiang on 2017/7/27.
 */

public interface FilmTopContract {
    interface Presenter extends BasePresenter {
        void getTop250(int start, int count, boolean isLoadMore);
    }

    interface View extends BaseView<Presenter> {
        void showTop250(Root root, boolean isLoadMore);

        void showNetError();
    }
}
