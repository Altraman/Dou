package com.example.huqiang.douban.mvp.bookdetail;

import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.mvp.BasePresenter;
import com.example.huqiang.douban.mvp.BaseView;

/**
 * Created by HuQiang on 2017/7/28.
 */

public interface BookDetailContract {
    interface Presenter extends BasePresenter {
        void getBookDetail(String id);
    }

    interface View extends BaseView<Presenter> {
        void showBookDetail(Books books);

        void showNetError();
    }
}
