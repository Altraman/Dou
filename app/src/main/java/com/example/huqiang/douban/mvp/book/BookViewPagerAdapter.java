package com.example.huqiang.douban.mvp.book;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.huqiang.douban.mvp.bookreading.BookReadingFragment;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class BookViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;

    public BookViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return BookReadingFragment.newInstance(position, titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
