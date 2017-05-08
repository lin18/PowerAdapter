package com.lin.poweradapter.example.staggered;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/5/2.
 */

public class StaggeredAdapter extends SingleAdapter<Staggered, StaggeredAdapter.ChildViewHolder> {

    public StaggeredAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.staggered_item);
    }

    @Override
    public void onBindVHolder(StaggeredAdapter.ChildViewHolder holder, int position) {
        final Staggered staggered = getItem(position);
        holder.title.setText(staggered.title);
        holder.subtitle.setText(staggered.subtitle);
        if (!TextUtils.isEmpty(staggered.more)) {
            holder.more.setHeight(50);
            holder.more.setText(staggered.more);
            holder.more.setVisibility(View.VISIBLE);
        } else {
            holder.more.setVisibility(View.GONE);
        }

    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subtitle)
        TextView subtitle;
        @BindView(R.id.more)
        TextView more;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }
}