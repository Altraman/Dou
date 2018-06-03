package com.example.huqiang.douban.mvp.musicdetail;

import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.music.musicdetails.Musics;
import com.example.huqiang.douban.data.source.remote.BookRemoteDataSource;
import com.example.huqiang.douban.data.source.remote.MusicRemoteDataSource;
import com.example.huqiang.douban.mvp.bookdetail.BookDetailContract;
import com.example.huqiang.douban.mvp.musiccontent.MusicContentContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class MusicDetailPresenter implements MusicDetailContract.Presenter {
    private CompositeDisposable disposable;
    private String musicId;
    private MusicRemoteDataSource dataSource;
    private MusicDetailContract.View view;

    public MusicDetailPresenter(String musicId, MusicRemoteDataSource dataSource, MusicDetailContract.View view) {
        this.musicId = musicId;
        this.dataSource = dataSource;
        this.view = view;
        this.view.setPresenter(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void onSubscribe() {
        getMusicDetail(musicId);
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

    @Override
    public void getMusicDetail(String id) {
        Disposable d = dataSource.getMusicDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Musics>() {
                    @Override
                    public void onNext(Musics value) {
                        view.showMusicDetail(value);
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
