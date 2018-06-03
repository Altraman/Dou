package com.example.huqiang.douban.data.music.musicsearch;

import com.example.huqiang.douban.data.music.musicdetails.Musics;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class MusicRoot {


    /**
     * count : 8
     * start : 0
     * total : 8
     * musics : []
     */

    private int count;
    private int start;
    private int total;
    private List<Musics> musics;

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

    public List<Musics> getMusics() {
        return musics;
    }

    public void setMusics(List<Musics> musics) {
        this.musics = musics;
    }
}
