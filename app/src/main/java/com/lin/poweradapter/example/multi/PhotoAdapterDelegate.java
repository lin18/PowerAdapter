package com.lin.poweradapter.example.multi;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.example.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/5/2.
 */

public abstract class PhotoAdapterDelegate extends AdapterDelegate<Chat, PowerViewHolder> {

    @Override
    protected void onBindViewHolder(@NonNull Chat item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        ChildViewHolder vh = (ChildViewHolder) holder;
        PhotoChat photoChat = (PhotoChat) item;
        vh.icon.setImageResource(photoChat.resId);
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
