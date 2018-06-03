package com.example.huqiang.douban.mvp.bookreading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.book.booksearch.BookRoot;
import com.example.huqiang.douban.data.source.remote.BookRemoteDataSource;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.mvp.bookdetail.BookDetailActivity;
import com.example.huqiang.douban.utils.BookApiUtils;
import com.example.huqiang.douban.utils.ScreenUtils;
import com.example.huqiang.douban.utils.ThemeUtils;
import com.example.huqiang.douban.widgets.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class BookReadingFragment extends Fragment implements BookReadingContract.View, SwipeRefreshLayout.OnRefreshListener {
    private BookReadingAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private BookReadingContract.Presenter presenter;
    private List<Books> books;
    private List<String> listTag;
    private int lastVisibleItem;
    private int position;

    public static BookReadingFragment newInstance(int position, String title) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("title", title);
        BookReadingFragment fragment = new BookReadingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_reading, container, false);
        initViews(view);
        new BookReadingPresenter(this, BookRemoteDataSource.getInstance());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }
        books = new ArrayList<>();
        String[] strTags = BookApiUtils.getApiTag(position);
        listTag = Arrays.asList(strTags);
        String tag = BookApiUtils.getRandomTAG(listTag);
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), ScreenUtils.dipToPx(getActivity(), 10), 0);
        recyclerView.addItemDecoration(spacesItemDecoration);
        presenter.getBooksByTag(tag, false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = manager.findLastVisibleItemPosition();
                    if (manager.getItemCount() == 1) {
                        if (adapter != null) {
                            adapter.updateStatus(adapter.LOAD_NONE);
                        }
                        return;
                    }
                    if (lastVisibleItem + 1 == manager.getItemCount()) {
                        if (adapter != null) {
                            adapter.updateStatus(adapter.LOAD_PULL_TO);
                            adapter.updateStatus(adapter.LOAD_MORE);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String tag = BookApiUtils.getRandomTAG(listTag);
                                presenter.getBooksByTag(tag, true);
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
    public void setPresenter(BookReadingContract.Presenter presenter) {
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
    public void showBooks(BookRoot bookRoot, boolean isLoadMore) {
        if (isLoadMore) {
            books.addAll(0, bookRoot.getBooks());
            adapter.notifyDataSetChanged();
        } else {
            books = bookRoot.getBooks();
            adapter = new BookReadingAdapter(books, getContext());
            recyclerView.setAdapter(adapter);
            adapter.setListener(new OnRecyclerViewItemClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    Intent intent = new Intent(getContext(), BookDetailActivity.class);
                    intent.putExtra(BookDetailActivity.EXTRA_ID, books.get(position).getId());
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
        String tag = BookApiUtils.getRandomTAG(listTag);
        presenter.getBooksByTag(tag, true);
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
