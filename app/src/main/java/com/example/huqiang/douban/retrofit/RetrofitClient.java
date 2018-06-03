package com.example.huqiang.douban.retrofit;

import com.example.huqiang.douban.MyApplication;
import com.example.huqiang.douban.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class RetrofitClient {
    private static Retrofit mInstance;

    public static Retrofit getInstance() {
        if (mInstance == null) {
            File httpCacheDirector = new File(MyApplication.getContext().getCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(httpCacheDirector, cacheSize);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .cache(cache)
                    .build();

            mInstance = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mInstance;
    }

    static Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if (!NetUtils.checkNetWorkIsAvailable(MyApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtils.checkNetWorkIsAvailable(MyApplication.getContext())) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
}
