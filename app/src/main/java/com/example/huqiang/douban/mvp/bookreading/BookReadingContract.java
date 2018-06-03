package com.example.huqiang.douban.mvp.bookreading;

import com.example.huqiang.douban.data.book.booksearch.BookRoot;
import com.example.huqiang.douban.mvp.BasePresenter;
import com.example.huqiang.douban.mvp.BaseView;

/**
 * Created by HuQiang on 2017/7/28.
 */

public interface BookReadingContract {
    interface Presenter extends BasePresenter {
        void getBooksByTag(String tag, boolean isLoadMore);
    }

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showBooks(BookRoot bookRoot, boolean isLoadMore);

        void showNetError();
    }
}
