package com.lin.poweradapter.example.stickyheaders;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.BaseViewHolder;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/4/28.
 */

public class StickyHeadersAdapter extends SingleAdapter<City, StickyHeadersAdapter.ChildViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    public StickyHeadersAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.stickyheader_item);
    }

    @Override
    public void onBindVHolder(ChildViewHolder holder, int position) {
        holder.title.setText(getItem(position).name);
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).pys.charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderViewHolder(parent, R.layout.item_pinned_header);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        String pys = getItem(position).pys;
        if (!TextUtils.isEmpty(pys) && pys.length() > 0) {
            if (holder != null && holder instanceof HeaderViewHolder && ((HeaderViewHolder) holder).city_tip != null)
                ((HeaderViewHolder) holder).city_tip.setText(pys.substring(0, 1));
        }
    }

    public int getLetterPosition(String letter){
        for (int i = 0 ; i < getItemCount(); i++){
            if(getItem(i).pys.startsWith(letter)){
                return i;
            }
        }
        return -1;
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.city_name)
        TextView title;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }

    static class HeaderViewHolder extends BaseViewHolder {

        @BindView(R.id.city_tip)
        TextView city_tip;

        HeaderViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }
}
