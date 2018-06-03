package com.example.huqiang.douban.mvp.bookdetail;

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
import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.data.source.remote.BookRemoteDataSource;
import com.example.huqiang.douban.ui.WebViewActivity;

public class BookDetailActivity extends AppCompatActivity implements BookDetailContract.View, View.OnClickListener {
    public static String EXTRA_ID = "id";
    private BookDetailContract.Presenter presenter;
    private Books books;

    private Toolbar toolbar;
    private ImageView ivBook;
    private AppCompatTextView tvBookName;
    private AppCompatTextView tvBookGrade;
    private AppCompatTextView tvBookArt;
    private AppCompatTextView tvBookPublishtime;
    private AppCompatTextView tvBookPublishAddress;
    private AppCompatTextView tvBookGradeNum;
    private AppCompatTextView tvWantRead;
    private AppCompatTextView tvMoreInfo;
    private AppCompatTextView tvDescription;
    private AppCompatTextView tvAuthorDescription;
    private AppCompatTextView tvChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        new BookDetailPresenter(getIntent().getStringExtra(EXTRA_ID), BookRemoteDataSource.getInstance(), this);
        initView();

        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("－。－看书");
        setSupportActionBar(toolbar);

        tvWantRead.setOnClickListener(this);
        tvMoreInfo.setOnClickListener(this);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivBook = (ImageView) findViewById(R.id.iv_book);
        tvBookName = (AppCompatTextView) findViewById(R.id.tv_book_name);
        tvBookGrade = (AppCompatTextView) findViewById(R.id.tv_book_grade);
        tvBookArt = (AppCompatTextView) findViewById(R.id.tv_book_art);
        tvBookPublishtime = (AppCompatTextView) findViewById(R.id.tv_book_publishtime);
        tvBookPublishAddress = (AppCompatTextView) findViewById(R.id.tv_book_publish_address);
        tvBookGradeNum = (AppCompatTextView) findViewById(R.id.tv_book_grade_num);
        tvWantRead = (AppCompatTextView) findViewById(R.id.tv_want_read);
        tvMoreInfo = (AppCompatTextView) findViewById(R.id.tv_more_info);
        tvDescription = (AppCompatTextView) findViewById(R.id.tv_description);
        tvAuthorDescription = (AppCompatTextView) findViewById(R.id.tv_author_description);
        tvChapters = (AppCompatTextView) findViewById(R.id.tv_chapters);
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
    public void initViews(View view) {

    }

    @Override
    public void setPresenter(BookDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showBookDetail(Books books) {
        this.books = books;
        if (books == null) {
            return;
        }
        if (books.getImages() != null) {
            Glide.with(this).load(books.getImages().getLarge()).into(ivBook);
        }
        if (!TextUtils.isEmpty(books.getTitle())) {
            tvBookName.setText(books.getTitle());
        }
        if (books.getAuthor() != null && books.getAuthor().size() > 0) {
            tvBookArt.setText(books.getAuthor().get(0));
        }
        if (!TextUtils.isEmpty(books.getPublisher())) {
            tvBookPublishAddress.setText(books.getPublisher());
        }
        if (!TextUtils.isEmpty(books.getPubdate())) {
            tvBookPublishtime.setText("出版时间" + books.getPubdate());
        }
        if (!TextUtils.isEmpty(books.getPublisher())) {
            tvBookPublishAddress.setText(books.getPublisher());
        }

        if (!TextUtils.isEmpty(books.getSummary())) {
            tvDescription.setText(books.getSummary());
        }
        if (!TextUtils.isEmpty(books.getAuthor_intro())) {
            tvAuthorDescription.setText(books.getAuthor_intro());
        }
        if (!TextUtils.isEmpty(books.getCatalog())) {
            tvChapters.setText(books.getCatalog());
        }
        if (!TextUtils.isEmpty(books.getRating().getAverage())) {
            tvBookGrade.setText(books.getRating().getAverage() + "分");
        }
        if (!TextUtils.isEmpty("" + books.getRating().getNumRaters())) {
            tvBookGradeNum.setText(books.getRating().getNumRaters() + "人评");
        }
    }

    @Override
    public void showNetError() {
        Toast.makeText(this, "网络出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_URL, books.getAlt());
        startActivity(intent);
    }
}
