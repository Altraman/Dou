package com.example.huqiang.douban.mvp.film;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huqiang.douban.R;
import com.example.huqiang.douban.mvp.BaseView;
import com.example.huqiang.douban.utils.ThemeUtils;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class FilmFragment extends Fragment implements BaseView {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FilmViewPagerAdapter adapter;
    private String[] titles = {"热销榜", "TOP250"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        initViews(view);

        adapter = new FilmViewPagerAdapter(getChildFragmentManager(), titles);
        viewPager.setAdapter(adapter);

        tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6), ThemeUtils.getThemeColor());
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    public static FilmFragment newInstance() {
        FilmFragment fragment = new FilmFragment();
        return fragment;
    }

    @Override
    public void initViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    @Override
    public void setPresenter(Object presenter) {

    }
}
