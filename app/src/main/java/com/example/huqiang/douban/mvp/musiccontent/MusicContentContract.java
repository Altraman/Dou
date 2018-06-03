package com.example.huqiang.douban.mvp.musiccontent;

import com.example.huqiang.douban.data.music.musicsearch.MusicRoot;
import com.example.huqiang.douban.mvp.BasePresenter;
import com.example.huqiang.douban.mvp.BaseView;

/**
 * Created by HuQiang on 2017/7/28.
 */

public interface MusicContentContract {
    interface Presenter extends BasePresenter {
        void getMusicsByTag(String tag, boolean isLoadMore, int refreshType);
    }

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showMusics(MusicRoot musicRoot, boolean isLoadMore, int refreshType);

        void showNetError();
    }
}
