package com.lin.poweradapter.example.headerfooter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.BaseViewHolder;
import com.lin.poweradapter.model.IMulti;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/5/2.
 */

public class FooterAdapterDelegate extends AdapterDelegate<IMulti, PowerViewHolder> {

    @Override
    protected boolean isForViewType(@NonNull IMulti item, int position) {
        return item instanceof Footer;
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.footer_layout);
    }

    @Override
    protected void onBindViewHolder(@NonNull IMulti item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        ChildViewHolder vh = (ChildViewHolder) holder;
        vh.icon.setImageResource(R.drawable.ic_visibility);
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }

}
