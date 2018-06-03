package com.example.huqiang.douban.mvp.filmdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.film.Casts;
import com.example.huqiang.douban.data.film.Directors;
import com.example.huqiang.douban.data.film.film_details.FilmDetail;
import com.example.huqiang.douban.data.film.film_top250.FilmPeople;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.ui.WebViewActivity;
import com.example.huqiang.douban.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuQiang on 2017/7/27.
 */

public class FilmDetailFragment extends Fragment implements FilmDetailContract.View, View.OnClickListener {
    private FilmDetailContract.Presenter presenter;
    private AppCompatTextView tv_title;
    private Toolbar toolbar;
    private ImageView iv_film;
    private AppCompatTextView tv_rating;
    private AppCompatTextView tv_rating_num;
    private AppCompatTextView tv_date_and_filmTime;
    private AppCompatTextView tv_filmType;
    private AppCompatTextView tv_filmCountry;
    private AppCompatTextView tv_filmName;
    private AppCompatTextView tv_description;
    private RecyclerView recyclerView;
    private AppCompatTextView tv_moreInfo;
    private List<FilmPeople> filmPeoples = new ArrayList<>();
    private String alt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_detail, container, false);
        initViews(view);

        iv_film.setOnClickListener(this);
        tv_moreInfo.setOnClickListener(this);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onSubscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        filmPeoples.clear();
        presenter.unSubscribe();
    }

    @Override
    public void initViews(View view) {
        tv_title = (AppCompatTextView) view.findViewById(R.id.tv_title);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        iv_film = (ImageView) view.findViewById(R.id.iv_film);
        tv_rating = (AppCompatTextView) view.findViewById(R.id.tv_rating);
        tv_rating_num = (AppCompatTextView) view.findViewById(R.id.tv_rating_num);
        tv_date_and_filmTime = (AppCompatTextView) view.findViewById(R.id.tv_date_and_film_time);
        tv_filmType = (AppCompatTextView) view.findViewById(R.id.tv_film_type);
        tv_filmCountry = (AppCompatTextView) view.findViewById(R.id.tv_film_country);
        tv_filmName = (AppCompatTextView) view.findViewById(R.id.tv_film_name);
        tv_description = (AppCompatTextView) view.findViewById(R.id.tv_description);
        tv_moreInfo = (AppCompatTextView) view.findViewById(R.id.tv_more_info);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        FilmDetailActivity activity = (FilmDetailActivity) getActivity();
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        toolbar.setNavigationIcon(R.drawable.back);
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                getActivity().overridePendingTransition(R.anim.trans_pre_in, R.anim.trans_pre_out);
                break;
        }
        return true;
    }

    @Override
    public void setPresenter(FilmDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFilmDetail(FilmDetail filmDetail) {
        if (filmDetail.getImages() != null && filmDetail.getImages().getLarge() != null) {
            Glide.with(getContext()).load(filmDetail.getImages().getLarge()).into(iv_film);
        }
        if (!TextUtils.isEmpty(filmDetail.getTitle())) {
            toolbar.setTitle(filmDetail.getTitle());
        }
        if (filmDetail.getRating() != null) {
            tv_rating.setText("评分" + filmDetail.getRating().getAverage());
        }
        tv_rating_num.setText(filmDetail.getRatings_count() + "人评分");
        tv_date_and_filmTime.setText(filmDetail.getYear() + "年  出品");
        if (filmDetail.getCountries() != null && filmDetail.getCountries().size() > 0) {
            tv_filmCountry.setText(filmDetail.getCountries().get(0));
        }
        if (filmDetail.getGenres() != null && filmDetail.getGenres().size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : filmDetail.getGenres()) {
                stringBuilder.append(s + "/");
            }
            tv_filmType.setText(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1));
        }
        tv_description.setText(filmDetail.getSummary());
        tv_filmName.setText(filmDetail.getOriginal_title() + " [原名]");
        alt = filmDetail.getAlt();
        initFilmData(filmDetail);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        FilmDetailPeopleAdapter adapter = new FilmDetailPeopleAdapter(getContext(), filmPeoples);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                String alt = filmPeoples.get(position).getAlt();
                intent.putExtra(WebViewActivity.EXTRA_URL, alt);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
            }
        });
    }

    private void initFilmData(FilmDetail filmDetail) {

        if (filmDetail.getDirectors() != null && filmDetail.getDirectors().size() > 0) {
            for (Directors directors :
                    filmDetail.getDirectors()) {
                FilmPeople filmPeople = new FilmPeople();
                filmPeople.setAlt(directors.getAlt());
                filmPeople.setAvatars(directors.getAvatars());
                filmPeople.setId(directors.getId());
                filmPeople.setName(directors.getName());
                filmPeople.setType(1);
                filmPeoples.add(filmPeople);
            }
        }

        if (filmDetail.getCasts() != null && filmDetail.getCasts().size() > 0) {
            for (Casts casts :
                    filmDetail.getCasts()) {
                FilmPeople filmPeople = new FilmPeople();
                filmPeople.setAlt(casts.getAlt());
                filmPeople.setAvatars(casts.getAvatars());
                filmPeople.setId(casts.getId());
                filmPeople.setName(casts.getName());
                filmPeople.setType(2);
                filmPeoples.add(filmPeople);
            }
        }
    }

    @Override
    public void showNetError() {
        Toast.makeText(getContext(), "网络出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_URL, alt);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }
}
