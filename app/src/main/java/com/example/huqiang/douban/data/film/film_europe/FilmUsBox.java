package com.example.huqiang.douban.data.film.film_europe;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class FilmUsBox {

    /**
     * date : 7月21日 - 7月23日
     * title : 豆瓣电影北美票房榜
     */

    private String date;
    private String title;
    private List<Subjects> subjects;

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
