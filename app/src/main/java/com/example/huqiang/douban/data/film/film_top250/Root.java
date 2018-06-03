package com.example.huqiang.douban.data.film.film_top250;

import com.example.huqiang.douban.data.film.Subject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Root {

    /**
     * count : 20
     * start : 0
     * total : 38
     */

    private int count;
    private int start;
    private int total;
    @SerializedName("subjects")
    private List<Subject> subjects;
    private String title;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
