package com.example.huqiang.douban.data.music.musicdetails;

import com.example.huqiang.douban.data.music.Rating;
import com.example.huqiang.douban.data.music.Tags;
import com.example.huqiang.douban.data.music.musicsearch.Attrs;
import com.example.huqiang.douban.data.music.musicsearch.Author;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Musics {

    /**
     * rating : {"max":10,"average":"8.7","numRaters":16,"min":0}
     * author : [{"name":"Unkle"}]
     * alt_title :
     * image : https://img3.doubanio.com/spic/s2716761.jpg
     * tags : [{"count":4,"name":"UNKLE"},{"count":4,"name":"Trip-Hop"},{"count":4,"name":"Electronic"},{"count":3,"name":"shadow"},{"count":3,"name":"my"},{"count":3,"name":"burn"},{"count":2,"name":"wonderful!!!超级棒！"},{"count":2,"name":"electro"}]
     * mobile_link : https://m.douban.com/music/subject/2136779/
     * attrs : {"publisher":["Surrender All"],"singer":["Unkle"],"discs":["1"],"pubdate":["2007-07-16"],"title":["Burn My Shadow"],"media":["Audio CD"],"tracks":["Radio Edit","Album Version","Unkle Surrender Sounds Session #5"],"version":["CD-single","Import"]}
     * title : Burn My Shadow
     * alt : https://music.douban.com/subject/2136779/
     * id : 2136779
     */

    private Rating rating;
    private String alt_title;
    private String image;
    private String mobile_link;
    private Attrs attrs;
    private String title;
    private String alt;
    private String id;
    private String summary;
    private List<Author> author;
    private List<Tags> tags;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Attrs getAttrs() {
        return attrs;
    }

    public void setAttrs(Attrs attrs) {
        this.attrs = attrs;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
