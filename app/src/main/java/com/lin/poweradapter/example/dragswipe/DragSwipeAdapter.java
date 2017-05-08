package com.lin.poweradapter.example.dragswipe;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.poweradapter.DragSwipeViewHolder;
import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lin18 on 2017/5/2.
 */

public class DragSwipeAdapter extends SingleAdapter<Analog, DragSwipeAdapter.ChildViewHolder> {

    public DragSwipeAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.dragswipe_item);
    }

    @Override
    public void onBindVHolder(DragSwipeAdapter.ChildViewHolder holder, int position) {
//        setTouchDragListener(holder.drag, holder);
        setLongClickDragListener(holder.drag, holder);
        final Analog analog = getItem(position);
        holder.title.setText(analog.text);
        if (analog.resId > 0) {
            holder.icon.setImageResource(analog.resId);
            holder.icon.setVisibility(View.VISIBLE);
        } else {
            holder.icon.setVisibility(View.GONE);
        }

    }

    static class ChildViewHolder extends DragSwipeViewHolder {

        Unbinder unbinder;

        @BindView(android.R.id.icon)
        ImageView icon;
        @BindView(android.R.id.title)
        TextView title;
        @BindView(R.id.drag)
        ImageView drag;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
            unbinder = ButterKnife.bind(this, itemView);
        }

    }
}