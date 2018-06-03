package com.example.huqiang.douban.mvp.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huqiang.douban.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class ThemeColorAdapter extends RecyclerView.Adapter<ThemeColorAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private int[] themeColors;
    private Context context;
    private boolean[] isSelected;
    private int nowPosition;

    public int getNowPosition() {
        return nowPosition;
    }

    public ThemeColorAdapter(int[] themeColors, Context context) {
        this.themeColors = themeColors;
        this.context = context;
        isSelected = new boolean[themeColors.length];
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < isSelected.length; i++) {
            isSelected[i] = false;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_theme_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.them_color.setImageResource(themeColors[position]);
        holder.them_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowPosition = position;
                isSelected[position] = true;
                if (isSelected[position]) {
                    holder.chosen.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                }
                isSelected[position] = false;
                holder.chosen.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return themeColors.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView them_color;
        ImageView chosen;

        public ViewHolder(View itemView) {
            super(itemView);
            them_color = (CircleImageView) itemView.findViewById(R.id.them_color);
            chosen = (ImageView) itemView.findViewById(R.id.chosen);
        }
    }
}
