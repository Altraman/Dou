package com.example.huqiang.douban.data.film.film_top250;

import com.example.huqiang.douban.data.film.Avatars;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class FilmPeople {

    /**
     * alt : https://movie.douban.com/celebrity/1077991/
     * avatars : {"small":"https://img1.doubanio.com/img/celebrity/small/1453574419.48.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1453574419.48.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1453574419.48.jpg"}
     * name : 张震
     * id : 1077991
     */

    private String alt;
    private Avatars avatars;
    private String name;
    private String id;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
