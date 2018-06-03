package com.example.huqiang.douban.mvp;

import android.view.View;

/**
 * Created by HuQiang on 2017/7/25.
 */

public interface BaseView<T> {
    void initViews(View view);

    void setPresenter(T presenter);
}
