package com.example.huqiang.douban.mvp.hotfilm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.film.Subject;
import com.example.huqiang.douban.data.source.remote.FilmRemoteDataSource;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.mvp.filmdetail.FilmDetailActivity;
import com.example.huqiang.douban.utils.ScreenUtils;
import com.example.huqiang.douban.utils.ThemeUtils;
import com.example.huqiang.douban.widgets.SpacesItemDecoration;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/26.
 */

public class FilmHotFragment extends Fragment implements FilmHotContract.View {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private FilmHotContract.Presenter presenter;
    private FilmHotAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_hot, container, false);
        new FilmHotPresenter(FilmRemoteDataSource.getInstance(), this);
        initViews(view);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getFilmLive();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onSubscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    public static FilmHotFragment newInstance() {
        return new FilmHotFragment();
    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), 0);
        recyclerView.addItemDecoration(spacesItemDecoration);
    }

    @Override
    public void setPresenter(FilmHotContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showFilmHot(final List<Subject> subject) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new FilmHotAdapter(getContext(), subject);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(getContext(), FilmDetailActivity.class);
                intent.putExtra(FilmDetailActivity.EXTRA_ID, subject.get(position).getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
            }
        });
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "网络出错了", Toast.LENGTH_SHORT).show();
    }
}
