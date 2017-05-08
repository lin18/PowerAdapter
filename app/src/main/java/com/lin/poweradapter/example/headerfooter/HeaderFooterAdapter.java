package com.lin.poweradapter.example.headerfooter;

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
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/5/2.
 */

public class HeaderFooterAdapter extends SingleAdapter<Analog, BaseViewHolder> {

    public HeaderFooterAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return true;
    }

    @Override
    public BaseViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.analog_item);
    }

    @Override
    protected BaseViewHolder getHeaderHolder(ViewGroup parent) {
        return new HeaderViewHolder(parent, R.layout.header_layout);
    }

    @Override
    protected BaseViewHolder getFooterHolder(ViewGroup parent) {
        return new FooterViewHolder(parent, R.layout.footer_layout);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == HEADER) {
            final HeaderViewHolder vh = (HeaderViewHolder) holder;
            vh.icon.setImageResource(R.drawable.ic_airplay);
        } else if (getItemViewType(position) == FOOTER) {
            final FooterViewHolder vh = (FooterViewHolder) holder;
            vh.icon.setImageResource(R.drawable.ic_visibility);
        }
    }

    @Override
    public void onBindVHolder(BaseViewHolder holder, int position) {
        final Analog analog = getItemExcludeHeader(position);
        final ChildViewHolder vh = (ChildViewHolder) holder;
        vh.title.setText(analog.text);
        if (analog.resId > 0) {
            vh.icon.setImageResource(analog.resId);
            vh.icon.setVisibility(View.VISIBLE);
        } else {
            vh.icon.setVisibility(View.GONE);
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

    static class HeaderViewHolder extends BaseViewHolder {
        @BindView(android.R.id.icon)
        ImageView icon;
        HeaderViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

    static class FooterViewHolder extends BaseViewHolder {
        @BindView(android.R.id.icon)
        ImageView icon;
        FooterViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }
}