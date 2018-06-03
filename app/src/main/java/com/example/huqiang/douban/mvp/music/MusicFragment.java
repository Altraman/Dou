package com.example.huqiang.douban.mvp.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huqiang.douban.R;
import com.example.huqiang.douban.mvp.BaseView;
import com.example.huqiang.douban.mvp.book.BookFragment;
import com.example.huqiang.douban.utils.MusicApiUtils;
import com.example.huqiang.douban.utils.ThemeUtils;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class MusicFragment extends Fragment implements BaseView {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] mTitles;
    private MusicViewPagerAdapter adapter;

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
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
        mTitles = MusicApiUtils.Music_Titles;
        Log.d("page", String.valueOf(mTitles.length));
        adapter = new MusicViewPagerAdapter(getChildFragmentManager(), mTitles);
        viewPager.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数
        viewPager.setOffscreenPageLimit(3);

        tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6), ThemeUtils.getThemeColor());
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void setPresenter(Object presenter) {

    }

}
