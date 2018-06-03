package com.example.huqiang.douban.data.source.remote;

import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.book.booksearch.BookRoot;
import com.example.huqiang.douban.data.music.musicdetails.Musics;
import com.example.huqiang.douban.data.music.musicsearch.MusicRoot;
import com.example.huqiang.douban.data.source.BookDataSource;
import com.example.huqiang.douban.data.source.MusicDataSource;
import com.example.huqiang.douban.retrofit.Api;
import com.example.huqiang.douban.retrofit.ApiServer;
import com.example.huqiang.douban.retrofit.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class MusicRemoteDataSource implements MusicDataSource {
    private static MusicRemoteDataSource mInstance;

    public static MusicRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new MusicRemoteDataSource();
        }
        return mInstance;
    }


    @Override
    public Observable<MusicRoot> searchMusicByTag(String tag) {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .searchMusicByTag(tag)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Musics> getMusicDetail(String id) {
        return RetrofitClient.getInstance()
                .create(ApiServer.class)
                .getMusicDetail(id)
                .subscribeOn(Schedulers.io());
    }
}
