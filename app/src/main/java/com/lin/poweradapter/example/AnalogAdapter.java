package com.lin.poweradapter.example;

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

import butterknife.BindView;

/**
 * Created by owp on 2017/5/2.
 */

public class AnalogAdapter extends SingleAdapter<Analog, AnalogAdapter.ChildViewHolder> {

    public AnalogAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return true;
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.analog_item);
    }

    @Override
    public void onBindVHolder(AnalogAdapter.ChildViewHolder holder, int position) {
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