package com.lin.poweradapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class AdapterDelegate<T, VH extends PowerViewHolder> {

  protected abstract boolean isForViewType(@NonNull T item, int position);

  @NonNull
  abstract protected VH onCreateViewHolder(ViewGroup parent);

  protected abstract void onBindViewHolder(@NonNull T item, int position,
                                           @NonNull VH holder, @NonNull List<Object> payloads);

  protected void onViewRecycled(@NonNull VH viewHolder) {
  }

  protected boolean onFailedToRecycleView(@NonNull VH holder) {
    return false;
  }

  protected void onViewAttachedToWindow(@NonNull VH holder) {
  }

  protected void onViewDetachedFromWindow(VH holder) {
  }

  public static View getItemView(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
    return LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
  }

}
