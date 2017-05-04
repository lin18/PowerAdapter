package com.lin.poweradapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewHolder 基类
 * Created by lin18 on 2017/4/8.
 */
public class PowerViewHolder extends RecyclerView.ViewHolder {

    View contentView;

    public PowerViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
        this(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
    }

    public PowerViewHolder(View itemView) {
        super(itemView);
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public final View getContentView() {
        return contentView != null ? contentView : itemView;
    }

}
