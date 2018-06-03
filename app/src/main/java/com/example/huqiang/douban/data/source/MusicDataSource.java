package com.example.huqiang.douban.data.source;

import com.example.huqiang.douban.data.music.musicdetails.Musics;
import com.example.huqiang.douban.data.music.musicsearch.MusicRoot;

import io.reactivex.Observable;

/**
 * Created by HuQiang on 2017/7/26.
 */

public interface MusicDataSource {
    Observable<MusicRoot> searchMusicByTag(String tag);

    Observable<Musics> getMusicDetail(String id);
}
