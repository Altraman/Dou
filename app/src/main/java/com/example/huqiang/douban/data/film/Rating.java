package com.example.huqiang.douban.data.film;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Rating {

    /**
     * max : 10
     * average : 7.6
     * stars : 40
     * min : 0
     */

    private int max;
    private double average;
    private String stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
