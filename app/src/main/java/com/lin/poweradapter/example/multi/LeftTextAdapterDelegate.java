package com.lin.poweradapter.example.multi;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.lin.poweradapter.example.R;

/**
 *
 * Created by owp on 2017/4/18.
 */

public class LeftTextAdapterDelegate extends TextAdapterDelegate {

    @Override
    protected boolean isForViewType(@NonNull Chat items, int position) {
        return items instanceof TextChat && !items.isFromMyself;
    }

    @NonNull
    @Override
    protected LeftViewHolder onCreateViewHolder(ViewGroup parent) {
        return new LeftViewHolder(parent, R.layout.left_text_chat_item);
    }

    static class LeftViewHolder extends TextAdapterDelegate.ChildViewHolder {

        LeftViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }
}
