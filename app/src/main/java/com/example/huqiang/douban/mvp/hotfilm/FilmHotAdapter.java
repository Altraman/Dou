package com.example.huqiang.douban.mvp.hotfilm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huqiang.douban.R;
import com.example.huqiang.douban.data.film.Subject;
import com.example.huqiang.douban.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.douban.utils.ScreenUtils;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/26.
 */

public class FilmHotAdapter extends RecyclerView.Adapter<FilmHotAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<Subject> subjects;
    private OnRecyclerViewItemClickListener listener;

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public FilmHotAdapter(Context context, List<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_film_hot, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(subjects.get(position).getTitle());

        ViewGroup.LayoutParams params = holder.tv_image.getLayoutParams();
        int width = ScreenUtils.getScreenWidthDp(context);
        int ivWidth = (width - ScreenUtils.dipToPx(context, 80)) / 3;
        params.width = ivWidth;
        double height = (420.0 / 300.0) * ivWidth;
        params.height = (int) height;
        holder.tv_image.setLayoutParams(params);
        Glide.with(context).load(subjects.get(position).getImages().getLarge()).into(holder.tv_image);

        if (!TextUtils.isEmpty(subjects.get(position).getRating().getAverage() + "")) {
            holder.tv_grade.setText("评分:" + String.valueOf(subjects.get(position).getRating().getAverage()));
        } else {
            holder.tv_grade.setText("暂无评分");
        }

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView tv_image;
        TextView tv_name;
        TextView tv_grade;
        private OnRecyclerViewItemClickListener listener;

        public ViewHolder(View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);
            tv_image = (ImageView) itemView.findViewById(R.id.tv_film);
            tv_name = (TextView) itemView.findViewById(R.id.tv_film_name);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_film_grade);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (this.listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }
}
