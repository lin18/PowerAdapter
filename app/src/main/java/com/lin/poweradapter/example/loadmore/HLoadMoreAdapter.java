package com.lin.poweradapter.example.loadmore;

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

public class HLoadMoreAdapter extends SingleAdapter<Analog, HLoadMoreAdapter.CVViewHolder> {

    public HLoadMoreAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return true;
    }

    @Override
    protected CVViewHolder getLoadingMoreHolder(ViewGroup parent) {
        return new CVViewHolder(parent, R.layout.h_progress_item);
    }

    @Override
    public CVViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.h_load_more_item);
    }

    @Override
    public void onBindVHolder(CVViewHolder holder, int position) {
        ChildViewHolder childViewHolder = (ChildViewHolder) holder;
        final Analog analog = getItem(position);
        childViewHolder.title.setText(analog.text);
        if (analog.resId > 0) {
            childViewHolder.icon.setImageResource(analog.resId);
            childViewHolder.icon.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.icon.setVisibility(View.GONE);
        }

    }

    static class LoadMoreViewHolder extends CVViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;
        @BindView(android.R.id.title)
        TextView title;

        LoadMoreViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }

    static class ChildViewHolder extends CVViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;
        @BindView(android.R.id.title)
        TextView title;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }

    static class CVViewHolder extends BaseViewHolder {

        CVViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }
}