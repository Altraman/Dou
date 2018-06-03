package com.example.huqiang.douban.mvp.top250film;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.film.film_top250.Root;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.utils.ScreenUtils;

/**
 * Created by HuQiang on 2017/7/27.
 */

public class FilmTopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Root root;
    private final LayoutInflater inflater;
    private OnRecyclerViewItemClickListener listener;
    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_NORMAL = -1;
    private static final int TYPE_FOOTER = -2;

    public FilmTopAdapter(Context context, Root root) {
        this.context = context;
        this.root = root;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(inflater.inflate(R.layout.view_footer, parent, false));
        } else
            return new NormalViewHolder(inflater.inflate(R.layout.item_film_top, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (status) {
                case LOAD_MORE:
                    footerViewHolder.progressBar.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_load_prompt.setText("正在加载...");
                    break;
                case LOAD_PULL_TO:
                    footerViewHolder.progressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_load_prompt.setText("上拉加载更多");
                    break;
                case LOAD_NONE:
                    footerViewHolder.progressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_load_prompt.setText("已无更多加载");
                    break;
                case LOAD_END:
                    break;
                default:
                    break;
            }
        } else if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            Glide.with(context).load(root.getSubjects().get(position).getImages().getLarge()).into(normalViewHolder.iv_film);
            normalViewHolder.tv_film.setText(root.getSubjects().get(position).getTitle());
            normalViewHolder.tv_film_english.setText(root.getSubjects().get(position).getOriginal_title());
            normalViewHolder.tv_film_grade.setText("评分:" + root.getSubjects().get(position).getRating().getAverage());
            if (position < 9) {
                normalViewHolder.tv_rank.setText("0" + (position + 1));
            } else {
                normalViewHolder.tv_rank.setText(position + 1 + "");
            }
        }
    }

    @Override
    public int getItemCount() {
        return root.getSubjects().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_load_prompt;
        ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            tv_load_prompt = (AppCompatTextView) itemView.findViewById(R.id.tv_load_prompt);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dipToPx(context, 40));
            itemView.setLayoutParams(params);
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_film;
        AppCompatTextView tv_film;
        AppCompatTextView tv_film_english;
        AppCompatTextView tv_film_grade;
        AppCompatTextView tv_rank;
        private OnRecyclerViewItemClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            iv_film = (ImageView) itemView.findViewById(R.id.iv_film);
            tv_film_english = (AppCompatTextView) itemView.findViewById(R.id.tv_film_english);
            tv_film_grade = (AppCompatTextView) itemView.findViewById(R.id.tv_film_grade);
            tv_rank = (AppCompatTextView) itemView.findViewById(R.id.tv_rank);
            tv_film = (AppCompatTextView) itemView.findViewById(R.id.tv_film);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v, getLayoutPosition());
        }
    }

    public void updateStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }
}
