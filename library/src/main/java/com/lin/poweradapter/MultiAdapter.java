package com.lin.poweradapter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 * Created by lin18 on 2017/4/8.
 */

public abstract class MultiAdapter<T, VH extends PowerViewHolder> extends SingleAdapter<T, VH> {

    @NonNull
    protected AdapterDelegatesManager<T, VH> delegatesManager;

    public MultiAdapter(@NonNull List<T> items) {
        super(items);
        this.delegatesManager = new AdapterDelegatesManager<>();
    }

    public MultiAdapter(@Nullable Object listener) {
        super(listener);
        this.delegatesManager = new AdapterDelegatesManager<>();
    }

    public MultiAdapter(@Nullable Object listener, @NonNull List<T> items) {
        super(listener, items);
        this.delegatesManager = new AdapterDelegatesManager<>();
    }

    @CallSuper
    @Override
    public VH onCreateVHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @CallSuper
    @Override
    public void onBindVHolder(VH holder, int position) {
        delegatesManager.onBindViewHolder(getItem(position), position, isSelected(position), holder, null);
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        if (getItemViewType(position) == LOAD_MORE) {

        } else if (getItemViewType(position) == HEADER) {

        } else if (getItemViewType(position) == FOOTER) {

        } else {
            setListener(holder, position);
            delegatesManager.onBindViewHolder(getItem(position), position, isSelected(position), holder, payloads);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getDataItemCount() > 0 && position < getItemCountContainHeader()) {
            return delegatesManager.getItemViewType(getItem(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
        delegatesManager.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        super.onFailedToRecycleView(holder);
        return delegatesManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        delegatesManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
        delegatesManager.onViewDetachedFromWindow(holder);
    }

}
