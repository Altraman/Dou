package com.example.huqiang.douban.mvp.bookreading;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.book.bookdetails.Books;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.utils.ScreenUtils;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/28.
 */

public class BookReadingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Books> bookses;
    private Context context;
    private OnRecyclerViewItemClickListener listener;

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_NORMAL = -1;
    private static final int TYPE_FOOTER = -2;

    public BookReadingAdapter(List<Books> bookses, Context context) {
        this.bookses = bookses;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(inflater.inflate(R.layout.view_footer, parent, false));
        } else {
            return new NormalViewHolder(inflater.inflate(R.layout.item_book_reading, parent, false), listener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (status) {
                case LOAD_MORE:
                    footerViewHolder.progressBar.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_load_promote.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_load_promote.setText("正在加载...");
                    break;
                case LOAD_PULL_TO:
                    footerViewHolder.progressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_load_promote.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_load_promote.setText("上拉加载更多");
                    break;
                case LOAD_NONE:
                    footerViewHolder.progressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_load_promote.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_load_promote.setText("已无更多加载");
                    break;
                case LOAD_END:
                    break;
                default:
                    break;
            }
        } else if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            ViewGroup.LayoutParams params = normalViewHolder.iv_book.getLayoutParams();
            int width = ScreenUtils.getScreenWidthDp(context);
            int ivWidth = (width - ScreenUtils.dipToPx(context, 80)) / 3;
            params.width = ivWidth;
            double height = ivWidth * (420.0 / 300.0);
            params.height = (int) height;
            normalViewHolder.iv_book.setLayoutParams(params);
            if (!TextUtils.isEmpty(bookses.get(position).getImages().getLarge())) {
                Glide.with(context).load(bookses.get(position).getImages().getLarge()).into(normalViewHolder.iv_book);
            }
            if (!TextUtils.isEmpty(bookses.get(position).getRating().getAverage())) {
                normalViewHolder.tv_book_grade.setText("评分:" + bookses.get(position).getRating().getAverage());
            } else {
                normalViewHolder.tv_book_grade.setText("暂无评分");
            }
            if (!TextUtils.isEmpty(bookses.get(position).getTitle())) {
                normalViewHolder.tv_book_name.setText(bookses.get(position).getTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        return bookses.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_book;
        AppCompatTextView tv_book_name;
        AppCompatTextView tv_book_grade;

        private OnRecyclerViewItemClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            iv_book = (ImageView) itemView.findViewById(R.id.iv_book);
            tv_book_name = (AppCompatTextView) itemView.findViewById(R.id.tv_book_name);
            tv_book_grade = (AppCompatTextView) itemView.findViewById(R.id.tv_book_grade);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v, getLayoutPosition());
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        AppCompatTextView tv_load_promote;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            tv_load_promote = (AppCompatTextView) itemView.findViewById(R.id.tv_load_prompt);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dipToPx(context, 40));
            itemView.setLayoutParams(params);
        }
    }

    public void updateStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }
}
