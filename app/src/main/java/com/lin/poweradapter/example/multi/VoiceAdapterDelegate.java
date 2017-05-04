package com.lin.poweradapter.example.multi;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.example.BaseViewHolder;

import java.util.List;


/**
 * Created by owp on 2017/5/2.
 */

public abstract class VoiceAdapterDelegate extends AdapterDelegate<Chat, PowerViewHolder> {

    @Override
    protected void onBindViewHolder(@NonNull Chat item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
    }

    static class ChildViewHolder extends BaseViewHolder {

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
