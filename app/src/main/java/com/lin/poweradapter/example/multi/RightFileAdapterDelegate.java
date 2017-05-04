package com.lin.poweradapter.example.multi;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.lin.poweradapter.example.R;

/**
 *
 * Created by owp on 2017/4/18.
 */

public class RightFileAdapterDelegate extends FileAdapterDelegate {

    @Override
    protected boolean isForViewType(@NonNull Chat items, int position) {
        return items instanceof FileChat && items.isFromMyself;
    }

    @NonNull
    @Override
    protected RightViewHolder onCreateViewHolder(ViewGroup parent) {
        return new RightViewHolder(parent, R.layout.right_file_chat_item);
    }

    static class RightViewHolder extends ChildViewHolder {

        RightViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }
}
