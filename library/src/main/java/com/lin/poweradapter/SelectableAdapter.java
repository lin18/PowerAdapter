package com.lin.poweradapter;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Created by lin18 on 2017/4/8.
 */

public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private static final String TAG = "SelectableAdapter";

    public static final int MODE_IDLE = 0, MODE_SINGLE = 1, MODE_MULTI = 2;

    @IntDef({MODE_IDLE, MODE_SINGLE, MODE_MULTI})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    @Mode
    private int mMode;

    private Set<Integer> mSelectedPositions;

    public SelectableAdapter() {
        mSelectedPositions = new TreeSet<>();
        mMode = MODE_IDLE;
    }

    public void setMode(@Mode int mode) {
        if (mMode == MODE_SINGLE && mode == MODE_IDLE)
            clearSelection();
        this.mMode = mode;
    }

    @Mode
    public int getMode() {
        return mMode;
    }

    public boolean isSelected(@IntRange(from = 0) int position) {
        return mSelectedPositions.contains(position);
    }

    public abstract boolean isSelectable(@IntRange(from = 0) int position);

    public void toggleSelection(@IntRange(from = 0) int position) {
        if (position < 0) return;
        if (mMode == MODE_SINGLE)
            clearSelection();

        boolean contains = mSelectedPositions.contains(position);
        if (contains) {
            removeSelection(position);
        } else {
            addSelection(position);
        }
        notifyItemChanged(position);
    }

    final boolean addAdjustedSelection(@IntRange(from = 0) int position) {
        return mSelectedPositions.add(position);
    }

    public final boolean removeSelection(@IntRange(from = 0) int position) {
        return mSelectedPositions.remove(position);
    }

    public final boolean addSelection(@IntRange(from = 0) int position) {
        return isSelectable(position) && mSelectedPositions.add(position);
    }

    public void selectAll(Integer... viewTypes) {
        List<Integer> viewTypesToSelect = Arrays.asList(viewTypes);
        int positionStart = 0, itemCount = 0;
        for (int i = 0; i < getItemCount(); i++) {
            if (isSelectable(i) &&
                    (viewTypesToSelect.isEmpty() || viewTypesToSelect.contains(getItemViewType(i)))) {
                mSelectedPositions.add(i);
                itemCount++;
            } else {
                // Optimization for ItemRangeChanged
                if (positionStart + itemCount == i) {
                    notifySelectionChanged(positionStart, itemCount);
                    itemCount = 0;
                    positionStart = i;
                }
            }
        }
        notifySelectionChanged(positionStart, getItemCount());
    }

    public void clearSelection() {
        Iterator<Integer> iterator = mSelectedPositions.iterator();
        int positionStart = 0, itemCount = 0;
        // The notification is done only on items that are currently selected.
        while (iterator.hasNext()) {
            int position = iterator.next();
            iterator.remove();
            // Optimization for ItemRangeChanged
            if (positionStart + itemCount == position) {
                itemCount++;
            } else {
                // Notify previous items in range
                notifySelectionChanged(positionStart, itemCount);
                positionStart = position;
                itemCount = 1;
            }
        }
        // Notify remaining items in range
        notifySelectionChanged(positionStart, itemCount);
    }

    private void notifySelectionChanged(int positionStart, int itemCount) {
        if (itemCount > 0) {
            notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    public int getSelectedItemCount() {
        return mSelectedPositions.size();
    }

    public List<Integer> getSelectedPositions() {
        return new ArrayList<>(mSelectedPositions);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(TAG, new ArrayList<>(mSelectedPositions));
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mSelectedPositions.addAll(savedInstanceState.getIntegerArrayList(TAG));
    }

    @CallSuper
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setActivated(isSelected(position));
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
    }

}
