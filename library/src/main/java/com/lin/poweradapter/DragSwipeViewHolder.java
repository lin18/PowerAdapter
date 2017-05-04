package com.lin.poweradapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * Created by lin18 on 2017/4/24.
 */

public class DragSwipeViewHolder extends PowerViewHolder implements ItemTouchHelperViewHolder {

    public DragSwipeViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
        super(parent, layoutResId);
    }

    public DragSwipeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onItemSelected() {
        itemView.setActivated(true);
    }

    @Override
    public void onItemClear() {
        itemView.setActivated(false);
    }
}
