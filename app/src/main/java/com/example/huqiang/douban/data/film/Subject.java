package com.example.huqiang.douban.data.film;

import com.example.huqiang.douban.data.Images;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Subject {

    /**
     * rating : {"max":10,"average":8.5,"stars":"45","min":0}
     * genres : ["剧情","历史","战争"]
     * title : 敦刻尔克
     * casts : [{"alt":"https://movie.douban.com/celebrity/1355522/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1494157438.59.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1494157438.59.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1494157438.59.jpg"},"name":"菲昂·怀特海德","id":"1355522"},{"alt":"https://movie.douban.com/celebrity/1376362/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1499056832.63.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1499056832.63.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1499056832.63.jpg"},"name":"汤姆·格林-卡尼","id":"1376362"},{"alt":"https://movie.douban.com/celebrity/1344553/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/HY1C0tZWJs0cel_avatar_uploaded1415349619.05.jpg","large":"https://img3.doubanio.com/img/celebrity/large/HY1C0tZWJs0cel_avatar_uploaded1415349619.05.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/HY1C0tZWJs0cel_avatar_uploaded1415349619.05.jpg"},"name":"杰克·劳登","id":"1344553"}]
     * collect_count : 5899
     * original_title : Dunkirk
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1054524/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/673.jpg","large":"https://img3.doubanio.com/img/celebrity/large/673.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/673.jpg"},"name":"克里斯托弗·诺兰","id":"1054524"}]
     * year : 2017
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2492932646.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2492932646.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2492932646.webp"}
     * alt : https://movie.douban.com/subject/26607693/
     * id : 26607693
     */

    private Rating rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private Images images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<Casts> casts;
    private List<Directors> directors;

    public Rating getRating() {
        return rating;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<Casts> getCasts() {
        return casts;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

}
