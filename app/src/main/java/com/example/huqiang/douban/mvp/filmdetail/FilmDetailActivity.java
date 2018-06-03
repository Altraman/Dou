package com.example.huqiang.douban.mvp.filmdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.source.remote.FilmRemoteDataSource;

public class FilmDetailActivity extends AppCompatActivity {
    public static String EXTRA_ID = "id";
    private FilmDetailFragment filmDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contain_main);

        if (savedInstanceState != null) {
            filmDetailFragment = (FilmDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState, "FilmDetailFragment");
        } else {
            filmDetailFragment = new FilmDetailFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, filmDetailFragment)
                .commit();
        new FilmDetailPresenter(getIntent().getStringExtra(EXTRA_ID)
                , FilmRemoteDataSource.getInstance()
                , filmDetailFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "FilmDetailFragment", filmDetailFragment);
    }
}
