package com.example.huqiang.douban.mvp.musicdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.music.musicdetails.Musics;
import com.example.huqiang.douban.data.source.remote.BookRemoteDataSource;
import com.example.huqiang.douban.data.source.remote.MusicRemoteDataSource;
import com.example.huqiang.douban.mvp.bookdetail.BookDetailPresenter;
import com.example.huqiang.douban.ui.WebViewActivity;

public class MusicDetailActivity extends AppCompatActivity implements MusicDetailContract.View, View.OnClickListener {
    public static String EXTRA_ID = "id";
    private MusicDetailContract.Presenter presenter;

    private AppCompatTextView tvTitle;
    private AppCompatTextView tvRight;
    private Toolbar toolbar;
    private ImageView ivMusic;
    private AppCompatTextView tvMusicName;
    private AppCompatTextView tvMusicArt;
    private AppCompatTextView tvMusicPublishtime;
    private AppCompatTextView tvMusicGrade;
    private AppCompatTextView tvMusicGradeNum;
    private AppCompatTextView tvListen;
    private AppCompatTextView tvMoreInfo;
    private AppCompatTextView tvDescription;
    private AppCompatTextView tvSongs;

    private Musics musics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
        new MusicDetailPresenter(getIntent().getStringExtra(EXTRA_ID), MusicRemoteDataSource.getInstance(), this);
        initView();

        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("－。－音乐");
        setSupportActionBar(toolbar);

        tvListen.setOnClickListener(this);
        tvMoreInfo.setOnClickListener(this);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivMusic = (ImageView) findViewById(R.id.iv_music);
        tvTitle = (AppCompatTextView) findViewById(R.id.tv_title);
        tvRight = (AppCompatTextView) findViewById(R.id.tv_right);
        tvMusicName = (AppCompatTextView) findViewById(R.id.tv_music_name);
        tvMusicArt = (AppCompatTextView) findViewById(R.id.tv_music_art);
        tvMusicPublishtime = (AppCompatTextView) findViewById(R.id.tv_music_publishtime);
        tvMusicGrade = (AppCompatTextView) findViewById(R.id.tv_music_grade);
        tvMusicGradeNum = (AppCompatTextView) findViewById(R.id.tv_music_grade_num);
        tvMoreInfo = (AppCompatTextView) findViewById(R.id.tv_more_info);
        tvDescription = (AppCompatTextView) findViewById(R.id.tv_description);
        tvListen = (AppCompatTextView) findViewById(R.id.tv_listen);
        tvSongs = (AppCompatTextView) findViewById(R.id.tv_songs);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onSubscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    public void setPresenter(MusicDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMusicDetail(Musics musics) {
        this.musics = musics;
        Glide.with(this).load(musics.getImage()).into(ivMusic);
        tvMusicName.setText(musics.getTitle());
        if (musics.getAuthor() != null && musics.getAuthor().size() > 0) {
            tvMusicArt.setText("艺术家：" + musics.getAuthor().get(0).getName());
        }
        if (musics.getAttrs() != null && musics.getAttrs().getPubdate() != null && musics.getAttrs().getPubdate().size() > 0) {
            tvMusicPublishtime.setText(musics.getAttrs().getPubdate().get(0));
        }
        if (musics.getRating() != null) {
            if (!TextUtils.isEmpty(musics.getRating().getAverage()))
                tvMusicGrade.setText(musics.getRating().getAverage());
            if (!TextUtils.isEmpty("" + musics.getRating().getNumRaters())) {
                tvMusicGradeNum.setText(musics.getRating().getNumRaters() + "人评");
            }
        }
        if (!TextUtils.isEmpty(musics.getSummary())) {
            tvDescription.setText(musics.getSummary());
        }
        if (musics.getAttrs().getTracks() != null && musics.getAttrs().getTracks().size() > 0) {
            tvSongs.setText(musics.getAttrs().getTracks().get(0));
        }
    }

    @Override
    public void showNetError() {
        Toast.makeText(this, "网络出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_URL, musics.getAlt());
        startActivity(intent);
    }
}
