package com.example.huqiang.douban.retrofit;

import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.book.booksearch.BookRoot;
import com.example.huqiang.douban.data.film.film_details.FilmDetail;
import com.example.huqiang.douban.data.film.film_europe.FilmUsBox;
import com.example.huqiang.douban.data.film.film_hot.FilmLive;
import com.example.huqiang.douban.data.film.film_top250.Root;
import com.example.huqiang.douban.data.music.musicdetails.Musics;
import com.example.huqiang.douban.data.music.musicsearch.MusicRoot;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HuQiang on 2017/7/25.
 */

public interface ApiServer {
    @GET(Api.HOT_FILM)
    Observable<FilmLive> getLiveFilms();

    @GET(Api.EUR_FILM)
    Observable<FilmUsBox> getUsBox();

    @GET(Api.TOP250_FILM)
    Observable<Root> getTop250(@Query("start") int start, @Query("count") int count);

    @GET("v2/movie/subject/{id}")
    Observable<FilmDetail> getFilmDetail(@Path("id") String id);

    @GET(Api.BOOK_SEARCH)
    Observable<BookRoot> searchBookByTag(@Query("tag") String tag);

    @GET(Api.BOOK_DETAIL)
    Observable<Books> getBookDetail(@Path("id") String id);


    @GET(Api.MUSIC_SEARCH)
    Observable<MusicRoot> searchMusicByTag(@Query("tag") String tag);

    @GET(Api.MUSIC_DETAIL)
    Observable<Musics> getMusicDetail(@Path("id") String id);
}
