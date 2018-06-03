package com.example.huqiang.douban.retrofit;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Api {
    public static final String BASE_URL = "https://api.douban.com/";

    public static final String HOT_FILM = "v2/movie/in_theaters";

    public static final String EUR_FILM = "v2/movie/us_box";

    public static final String TOP250_FILM = "v2/movie/top250";

    public static final String FILM_DETAIL = "v2/movie/subject/{id}";

    public static final String BOOK_SEARCH = "v2/book/search";

    public static final String BOOK_DETAIL = "v2/book/{id}";

    public static final String MUSIC_SEARCH = "v2/music/search";

    public static final String MUSIC_DETAIL = "v2/music/{id}";
}
