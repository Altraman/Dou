package com.example.huqiang.douban.mvp.book;

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
import com.example.huqiang.douban.utils.BookApiUtils;
import com.example.huqiang.douban.utils.ThemeUtils;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class BookFragment extends Fragment implements BaseView {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] mTitles;
    private BookViewPagerAdapter adapter;

    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTitles = BookApiUtils.Tag_Titles;

        adapter = new BookViewPagerAdapter(getChildFragmentManager(), mTitles);
        viewPager.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数
        viewPager.setOffscreenPageLimit(4);

        tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6), ThemeUtils.getThemeColor());
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void setPresenter(Object presenter) {

    }
}
