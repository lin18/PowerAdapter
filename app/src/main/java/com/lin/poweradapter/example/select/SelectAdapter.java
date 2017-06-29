package com.lin.poweradapter.example.select;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.BaseViewHolder;
import com.lin.poweradapter.example.R;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/5/2.
 */

public class SelectAdapter extends SingleAdapter<Analog, SelectAdapter.ChildViewHolder> {

    public SelectAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return true;
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.item_multi_select);
    }

    @Override
    public void onBindVHolder(SelectAdapter.ChildViewHolder holder, int position) {
        final Analog analog = getItem(position);
        holder.title.setText(analog.text);
        holder.title.setChecked(isSelected(position));
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.title)
        CheckedTextView title;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }
}