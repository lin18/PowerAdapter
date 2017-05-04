package com.lin.poweradapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 * Created by lin18 on 2017/4/18.
 */

public class DefaultAdapterDelegate extends AdapterDelegate<Object, PowerViewHolder> {

    @Override
    protected boolean isForViewType(@NonNull Object item, int position) {
        return true;
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected void onBindViewHolder(@NonNull Object item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {

    }

}
