package com.lin.poweradapter.example.loadmore;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.BaseViewHolder;
import com.lin.poweradapter.example.R;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/5/2.
 */

public class LoadMoreAdapter extends SingleAdapter<Analog, LoadMoreAdapter.ChildViewHolder> {

    public LoadMoreAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return true;
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.load_more_item);
    }

    @Override
    public void onBindVHolder(LoadMoreAdapter.ChildViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Analog analog = getItem(position);
        holder.title.setText(analog.text);
        if (analog.resId > 0) {
            holder.icon.setImageResource(analog.resId);
            holder.icon.setVisibility(View.VISIBLE);
        } else {
            holder.icon.setVisibility(View.GONE);
        }

    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;
        @BindView(android.R.id.title)
        TextView title;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }
}