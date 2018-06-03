package com.example.huqiang.douban.mvp.film;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.huqiang.douban.mvp.hotfilm.FilmHotFragment;
import com.example.huqiang.douban.mvp.top250film.FilmTopFragment;

/**
 * Created by HuQiang on 2017/7/26.
 */

public class FilmViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Fragment[] fragments = {
            FilmHotFragment.newInstance(),
            FilmTopFragment.newInstance()
    };

    public FilmViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
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
