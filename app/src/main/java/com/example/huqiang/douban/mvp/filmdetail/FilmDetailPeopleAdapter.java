package com.example.huqiang.douban.mvp.filmdetail;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.film.film_top250.FilmPeople;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/27.
 */

public class FilmDetailPeopleAdapter extends RecyclerView.Adapter<FilmDetailPeopleAdapter.ViewHolder> {
    private OnRecyclerViewItemClickListener listener;
    private Context context;
    private final LayoutInflater inflater;
    private List<FilmPeople> filmPeoples;

    public FilmDetailPeopleAdapter(Context context, List<FilmPeople> filmPeoples) {
        this.context = context;
        this.filmPeoples = filmPeoples;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_film_people, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (filmPeoples.get(position).getAvatars().getLarge() != null) {
            Glide.with(context).load(filmPeoples.get(position).getAvatars().getLarge()).into(holder.ivAvatar);
        }
        holder.tvNameChinease.setText(filmPeoples.get(position).getName());
        if (filmPeoples.get(position).getType() == 1) {
            holder.tvNameEnglish.setText("［导演］");
        } else {
            holder.tvNameEnglish.setText("［演员］");
        }
    }

    @Override
    public int getItemCount() {
        return filmPeoples.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivAvatar;
        AppCompatTextView tvNameChinease;
        AppCompatTextView tvNameEnglish;
        private OnRecyclerViewItemClickListener listener;

        public ViewHolder(View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvNameChinease = (AppCompatTextView) itemView.findViewById(R.id.tv_name_chinease);
            tvNameEnglish = (AppCompatTextView) itemView.findViewById(R.id.tv_name_english);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v, getLayoutPosition());
        }
    }
}
