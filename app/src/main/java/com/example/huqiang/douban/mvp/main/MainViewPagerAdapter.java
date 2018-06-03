package com.example.huqiang.douban.mvp.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.huqiang.douban.mvp.book.BookFragment;
import com.example.huqiang.douban.mvp.film.FilmFragment;
import com.example.huqiang.douban.mvp.music.MusicFragment;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = {
            FilmFragment.newInstance(),
            BookFragment.newInstance(),
            MusicFragment.newInstance()
    };

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
