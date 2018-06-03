package com.example.huqiang.douban.mvp.musicdetail;

import com.example.huqiang.douban.data.music.musicdetails.Musics;
import com.example.huqiang.douban.mvp.BasePresenter;
import com.example.huqiang.douban.mvp.BaseView;

/**
 * Created by HuQiang on 2017/7/28.
 */

public interface MusicDetailContract {
    interface Presenter extends BasePresenter {
        void getMusicDetail(String id);
    }

    interface View extends BaseView<Presenter> {
        void showMusicDetail(Musics musics);

        void showNetError();
    }
}
