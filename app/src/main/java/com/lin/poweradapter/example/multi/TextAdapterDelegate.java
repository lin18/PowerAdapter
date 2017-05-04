package com.lin.poweradapter.example.multi;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by owp on 2017/5/2.
 */

public abstract class TextAdapterDelegate extends AdapterDelegate<Chat, PowerViewHolder> {

    @Override
    protected void onBindViewHolder(@NonNull Chat item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        ChildViewHolder vh = (ChildViewHolder) holder;
        TextChat textChat = (TextChat) item;
        vh.textView.setText(textChat.message);
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.text_view)
        TextView textView;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
