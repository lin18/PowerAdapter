package com.lin.poweradapter;

import android.support.annotation.CallSuper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Created by lin18 on 2017/4/8.
 */

public abstract class SingleAdapter<T, VH extends PowerViewHolder> extends AnimatorAdapter<VH>
        implements DataLoadingCallbacks, ItemTouchHelperAdapter {

    public final static int LOAD_MORE = -777;
    public final static int HEADER = -778;
    public final static int FOOTER = -779;

    private boolean isLoadMore = false;
    private boolean showLoadingMore = false, showHeader = false, showFooter = false;

    @Nullable
    public OnItemClickListener mItemClickListener;
    @Nullable
    public OnItemLongClickListener mItemLongClickListener;
    @Nullable
    public OnStartDragListener mDragStartListener;

    @NonNull
    private List<T> items;

    public SingleAdapter(@NonNull List<T> items) {
        this(null, items);
    }

    public SingleAdapter(@Nullable Object listener) {
        this(listener, new ArrayList<T>());
    }

    public SingleAdapter(@Nullable Object listener, @NonNull List<T> items) {
        super();
        this.items = items;
        addListener(listener);
    }

    @CallSuper
    public void addListener(@Nullable Object listener) {
        if (listener instanceof OnItemClickListener) {
            mItemClickListener = (OnItemClickListener) listener;
        }
        if (listener instanceof OnItemLongClickListener) {
            mItemLongClickListener = (OnItemLongClickListener) listener;
        }
        if (listener instanceof OnStartDragListener) {
            mDragStartListener = (OnStartDragListener) listener;
        }
    }

    @Override
    public long getItemId(int position) {
        final int type = getItemViewType(position);
        if (type == LOAD_MORE || type == HEADER || type == FOOTER)
            return type;
        return getItem(position).hashCode();
    }

    @Override
    public int getItemViewType(int position) {
        if (showHeader && position == 0)
            return HEADER;
        else if (getDataItemCount() > 0 && position < getItemCountContainHeader())
            return super.getItemViewType(position);
        else if (showFooter && position == getItemCountContainHeader())
            return FOOTER;
        else if (showLoadingMore)
            return LOAD_MORE;
        return -1;
    }

    int getItemCountContainHeader() {
        return getDataItemCount() + (showHeader ? 1 : 0);
    }

    int getLoadingMoreItemPosition() {
        return showLoadingMore ? (getItemCount() > 0 ? getItemCount() - 1 : 0) : RecyclerView.NO_POSITION;
    }

    public void setLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    @Override
    public boolean isDataLoading() {
        return isLoadMore;
    }

    @Override
    public void dataStartedLoading() {
        if (showLoadingMore) return;
        showLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());
    }

    @Override
    public void dataFinishedLoading() {
        if (!showLoadingMore) return;
        final int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
        notifyItemInserted(0);
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
        notifyItemInserted(getItemCount());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER)
            return getHeaderHolder(parent);
        else if (viewType == FOOTER)
            return getFooterHolder(parent);
        else if (viewType == LOAD_MORE)
            return getLoadingMoreHolder(parent);
        else
            return onCreateVHolder(parent, viewType);
    }

    protected VH getLoadingMoreHolder(ViewGroup parent) {
        return (VH) new PowerViewHolder(parent, R.layout.progress_item);
    }

    protected VH getHeaderHolder(ViewGroup parent) {
        return null;
    }

    protected VH getFooterHolder(ViewGroup parent) {
        return null;
    }

    public abstract VH onCreateVHolder(ViewGroup parent, int viewType);
    public abstract void onBindVHolder(final VH holder, final int position);

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == LOAD_MORE) {

        } else if (getItemViewType(position) == HEADER) {

        } else if (getItemViewType(position) == FOOTER) {

        } else {
            onBindVHolder(holder, position);
            setListener(holder, position);
        }
    }

    @CallSuper
    void setListener(final VH holder, final int position) {
        holder.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, holder);
            }
        });
        holder.getContentView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemLongClick(v, holder);
            }
        });
    }

    @CallSuper
    protected void onItemClick(View v, VH holder) {
        if (mItemClickListener != null)
            mItemClickListener.onItemClick(v, holder.getAdapterPosition());
    }

    @CallSuper
    protected boolean onItemLongClick(View v, VH holder) {
        if (mItemLongClickListener != null)
            return mItemLongClickListener.onItemLongClick(v, holder.getAdapterPosition());
        return false;
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

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    protected void setTouchDragListener(final View view, final RecyclerView.ViewHolder viewHolder) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    if (mDragStartListener != null)
                        mDragStartListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    protected void setLongClickDragListener(final View view, final RecyclerView.ViewHolder viewHolder) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mDragStartListener != null)
                    mDragStartListener.onStartDrag(viewHolder);
                return true;
            }
        });
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return false;
    }

    public int getDataItemCount() {
        return items == null ? 0 : items.size();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @Override
    public int getItemCount() {
        int extra = 0;
        if (showLoadingMore)
            extra++;
        if (showHeader)
            extra++;
        if (showFooter)
            extra++;
        return getDataItemCount() + extra;
    }

    @NonNull
    public List<T> getItems() {
        return items;
    }

    public T getItem(@IntRange(from = 0) int position) {
        return items.get(position);
    }

    public T getItemExcludeHeader(@IntRange(from = 0) int position) {
        return items.get(showHeader ? position - 1 : position);
    }

    @UiThread
    @CallSuper
    public void setItems(@NonNull List<T> items) {
        dataFinishedLoading();
        this.items = items;
        notifyDataSetChanged();
    }

    @UiThread
    @CallSuper
    public void remove(@NonNull T elem) {
        dataFinishedLoading();
        final int position = items.lastIndexOf(elem);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    @UiThread
    @CallSuper
    public void remove(@IntRange(from = 0) int position) {
        dataFinishedLoading();
        items.remove(position);
        notifyItemRemoved(position);
    }

    @UiThread
    @CallSuper
    public void replaceAll(@NonNull List<T> elem) {
        dataFinishedLoading();
        items.clear();
        items.addAll(elem);
        notifyDataSetChanged();
    }

    @UiThread
    @CallSuper
    public void addAll(@NonNull List<T> elem) {
        dataFinishedLoading();
        final int size = getItemCount();
        items.addAll(elem);
        notifyItemRangeInserted(size, getItemCount());
    }

    @UiThread
    @CallSuper
    public void addItems(@IntRange(from = 0) int position, @NonNull List<T> data) {
        dataFinishedLoading();
        items.addAll(position, data);
        notifyItemRangeInserted(position, data.size());
    }

    @UiThread
    @CallSuper
    public void addItem(@NonNull T elem) {
        dataFinishedLoading();
        items.add(elem);
        notifyItemInserted(getItemCount());
    }

    @UiThread
    @CallSuper
    public void addItem(@IntRange(from = 0) int position, @NonNull T elem) {
        dataFinishedLoading();
        items.add(position, elem);
        notifyItemInserted(position);
    }

    @UiThread
    @CallSuper
    public void setItem(@IntRange(from = 0) int position, @NonNull T elem) {
        dataFinishedLoading();
        items.set(position, elem);
        notifyItemChanged(position);
    }

    @UiThread
    @CallSuper
    public int update(@NonNull T elem) {
        dataFinishedLoading();
        final int index = items.indexOf(elem);
        if (index > -1) {
            setItem(index, elem);
        }
        return index;
    }

    @UiThread
    @CallSuper
    public void updateAndSwap(@NonNull T elem, @IntRange(from = 0) int to) {
        int index = update(elem);
        if (index > -1) {
            Collections.swap(items, index, to);
            notifyItemChanged(to);
        }
    }

    @UiThread
    @CallSuper
    public void swap(@IntRange(from = 0) int from, @IntRange(from = 0) int to) {
        dataFinishedLoading();
        Collections.swap(items, from, to);
        notifyItemChanged(from);
        notifyItemChanged(to);
    }

    @UiThread
    @CallSuper
    public void clear() {
        dataFinishedLoading();
        clearSelection();
        items.clear();
        notifyDataSetChanged();
    }

    @UiThread
    @CallSuper
    @Override
    public void onItemDismiss(@IntRange(from = 0) int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @UiThread
    @CallSuper
    @Override
    public boolean onItemMove(@IntRange(from = 0) int fromPosition, @IntRange(from = 0) int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View v, int position);
    }

}
