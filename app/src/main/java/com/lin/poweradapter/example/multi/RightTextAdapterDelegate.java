package com.lin.poweradapter.example.multi;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.lin.poweradapter.example.R;

/**
 *
 * Created by lin18 on 2017/4/18.
 */

public class RightTextAdapterDelegate extends TextAdapterDelegate {

    @Override
    protected boolean isForViewType(@NonNull Chat items, int position) {
        return items instanceof TextChat && items.isFromMyself;
    }

    @NonNull
    @Override
    protected RightViewHolder onCreateViewHolder(ViewGroup parent) {
        return new RightViewHolder(parent, R.layout.right_text_chat_item);
    }

    static class RightViewHolder extends TextAdapterDelegate.ChildViewHolder {

        RightViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }
}
