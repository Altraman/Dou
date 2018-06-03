package com.example.huqiang.douban.mvp.musiccontent;

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
import com.example.huqiang.douban.data.music.musicdetails.Musics;
import com.example.huqiang.douban.data.music.musicsearch.MusicRoot;
import com.example.huqiang.douban.data.source.remote.MusicRemoteDataSource;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.mvp.musicdetail.MusicDetailActivity;
import com.example.huqiang.douban.utils.MusicApiUtils;
import com.example.huqiang.douban.utils.ScreenUtils;
import com.example.huqiang.douban.utils.ThemeUtils;
import com.example.huqiang.douban.widgets.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class MusicContentFragment extends Fragment implements MusicContentContract.View, SwipeRefreshLayout.OnRefreshListener {
    private MusicContentAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private MusicContentContract.Presenter presenter;

    private List<Musics> musicses;
    private List<String> listTag;
    private int lastVisibleItem;
    private int position;

    private static final int NOT_PULL = 0;
    private static final int PULL_TOP = 1;
    private static final int PULL_DOWN = 2;

    public static MusicContentFragment newInstance(int position, String title) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("title", title);
        MusicContentFragment fragment = new MusicContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_content, container, false);
        initViews(view);
        new MusicContentPresenter(this, MusicRemoteDataSource.getInstance());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }
        musicses = new ArrayList<>();
        String[] strTags = MusicApiUtils.getApiTag(position);
        listTag = Arrays.asList(strTags);
        String tag = MusicApiUtils.getRandomTAG(listTag);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), 0);
        recyclerView.addItemDecoration(spacesItemDecoration);
        presenter.getMusicsByTag(tag, false, NOT_PULL);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = manager.findLastVisibleItemPosition();
                    if (manager.getItemCount() == 1) {
                        if (adapter != null) {
                            adapter.updateStatus(MusicContentAdapter.LOAD_NONE);
                        }
                        return;
                    }
                    if (lastVisibleItem + 1 == manager.getItemCount()) {
                        if (adapter != null) {
                            adapter.updateStatus(MusicContentAdapter.LOAD_PULL_TO);
                            adapter.updateStatus(MusicContentAdapter.LOAD_MORE);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String tag = MusicApiUtils.getRandomTAG(listTag);
                                presenter.getMusicsByTag(tag, true, PULL_DOWN);
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        refreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void setPresenter(MusicContentContract.Presenter presenter) {
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
    public void showMusics(MusicRoot musicRoot, boolean isLoadMore, int refreshType) {
        if (isLoadMore) {
            switch (refreshType) {
                case PULL_TOP:
                    musicses.addAll(0, musicRoot.getMusics());
                    break;
                case PULL_DOWN:
                    musicses.addAll(musicRoot.getMusics());
                    break;
                default:
                    break;
            }
            adapter.notifyDataSetChanged();
        } else {
            musicses = musicRoot.getMusics();
            adapter = new MusicContentAdapter(musicses, getContext());
            recyclerView.setAdapter(adapter);
            adapter.setListener(new OnRecyclerViewItemClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    Intent intent = new Intent(getContext(), MusicDetailActivity.class);
                    intent.putExtra(MusicDetailActivity.EXTRA_ID, musicses.get(position).getId());
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
        String tag = MusicApiUtils.getRandomTAG(listTag);
        presenter.getMusicsByTag(tag, true, PULL_TOP);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout != null) {
                    refreshLayout.setRefreshing(false);
                }
            }
        });
    }
}
