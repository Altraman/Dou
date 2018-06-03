package com.example.huqiang.douban;

import android.app.Application;
import android.content.Context;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
