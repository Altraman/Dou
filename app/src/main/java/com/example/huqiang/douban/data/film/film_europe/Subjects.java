package com.example.huqiang.douban.data.film.film_europe;

import com.example.huqiang.douban.data.film.Subject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Subjects {

    /**
     * box : 50500000
     * new : true
     * rank : 1
     */

    private int box;
    @SerializedName("new")
    private boolean newX;
    private int rank;
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
