package com.example.huqiang.douban.mvp.top250film;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.film.film_top250.Root;
import com.example.huqiang.douban.data.source.remote.FilmRemoteDataSource;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.mvp.filmdetail.FilmDetailActivity;
import com.example.huqiang.douban.utils.ThemeUtils;

/**
 * Created by HuQiang on 2017/7/26.
 */

public class FilmTopFragment extends Fragment implements FilmTopContract.View, SwipeRefreshLayout.OnRefreshListener {

    private FilmTopContract.Presenter presenter;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private int pageCount = 0;
    private final int pageSize = 10;
    private int lastVisibleItem;
    private Root mRoot;
    private FilmTopAdapter adapter;

    public static FilmTopFragment newInstance() {
        FilmTopFragment fragment = new FilmTopFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_hot, container, false);
        initViews(view);
        new FilmTopPresenter(FilmRemoteDataSource.getInstance(), this);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.getTop250(pageCount * pageSize, pageSize, false);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = manager.findLastVisibleItemPosition();
                    if (manager.getItemCount() == 1) {
                        if (adapter != null) {
                            adapter.updateStatus(FilmTopAdapter.LOAD_NONE);
                        }
                        return;
                    }
                    if (lastVisibleItem + 1 == manager.getItemCount()) {
                        if (adapter != null) {
                            adapter.updateStatus(FilmTopAdapter.LOAD_PULL_TO);
                            adapter.updateStatus(FilmTopAdapter.LOAD_MORE);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pageCount++;
                                presenter.getTop250(pageCount * pageSize, pageSize, true);
                            }
                        }, 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
    }


    @Override
    public void initViews(View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        refreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void setPresenter(FilmTopContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTop250(Root root, boolean isLoadMore) {
        if (isLoadMore) {
            mRoot.getSubjects().addAll(root.getSubjects());
            adapter.notifyDataSetChanged();
        } else {
            mRoot = root;
            adapter = new FilmTopAdapter(getContext(), mRoot);
            recyclerView.setAdapter(adapter);
            adapter.setListener(new OnRecyclerViewItemClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    Intent intent = new Intent(getContext(), FilmDetailActivity.class);
                    intent.putExtra(FilmDetailActivity.EXTRA_ID, mRoot.getSubjects().get(position).getId());
                    startActivity(intent);
                }
            });
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNetError() {
        Toast.makeText(getContext(), "网络出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        if (mRoot.getSubjects() == null) {
            presenter.getTop250(0, pageSize, false);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }
}
