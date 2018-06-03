package com.example.huqiang.douban.data.film;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Casts {

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
