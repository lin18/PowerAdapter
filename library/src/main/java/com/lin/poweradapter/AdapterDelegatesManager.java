package com.lin.poweradapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public class AdapterDelegatesManager<T, VH extends PowerViewHolder> {

  static final int FALLBACK_DELEGATE_VIEW_TYPE = Integer.MAX_VALUE - 1;

  private static final List<Object> PAYLOADS_EMPTY_LIST = Collections.emptyList();

  protected SparseArrayCompat<AdapterDelegate<T, VH>> delegates = new SparseArrayCompat();
  protected AdapterDelegate<T, PowerViewHolder> fallbackDelegate;

  public AdapterDelegatesManager<T, VH> addDelegate(@NonNull AdapterDelegate<T, VH> delegate) {
    // algorithm could be improved since there could be holes,
    // but it's very unlikely that we reach Integer.MAX_VALUE and run out of unused indexes
    int viewType = delegates.size();
    while (delegates.get(viewType) != null) {
      viewType++;
      if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
        throw new IllegalArgumentException(
            "Oops, we are very close to Integer.MAX_VALUE. It seems that there are no more free and unused view type integers left to add another AdapterDelegate.");
      }
    }
    return addDelegate(viewType, false, delegate);
  }

  public AdapterDelegatesManager<T, VH> addDelegate(int viewType,
      @NonNull AdapterDelegate<T, VH> delegate) {
    return addDelegate(viewType, false, delegate);
  }

  public AdapterDelegatesManager<T, VH> addDelegate(int viewType, boolean allowReplacingDelegate,
      @NonNull AdapterDelegate<T, VH> delegate) {

    if (delegate == null) {
      throw new NullPointerException("AdapterDelegate is null!");
    }

    if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
      throw new IllegalArgumentException("The view type = "
          + FALLBACK_DELEGATE_VIEW_TYPE
          + " is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view type.");
    }

    if (!allowReplacingDelegate && delegates.get(viewType) != null) {
      throw new IllegalArgumentException(
          "An AdapterDelegate is already registered for the viewType = "
              + viewType
              + ". Already registered AdapterDelegate is "
              + delegates.get(viewType));
    }

    delegates.put(viewType, delegate);

    return this;
  }

  public AdapterDelegatesManager<T, VH> removeDelegate(@NonNull AdapterDelegate<T, VH> delegate) {

    if (delegate == null) {
      throw new NullPointerException("AdapterDelegate is null");
    }

    int indexToRemove = delegates.indexOfValue(delegate);

    if (indexToRemove >= 0) {
      delegates.removeAt(indexToRemove);
    }
    return this;
  }

  public AdapterDelegatesManager<T, VH> removeDelegate(int viewType) {
    delegates.remove(viewType);
    return this;
  }

  public int getItemViewType(@NonNull T items, int position) {

    if (items == null) {
      throw new NullPointerException("Items datasource is null!");
    }

    int delegatesCount = delegates.size();
    for (int i = 0; i < delegatesCount; i++) {
      AdapterDelegate<T, VH> delegate = delegates.valueAt(i);
      if (delegate.isForViewType(items, position)) {
        return delegates.keyAt(i);
      }
    }

    if (fallbackDelegate != null) {
      return FALLBACK_DELEGATE_VIEW_TYPE;
    }

    throw new NullPointerException(
              "No AdapterDelegate added that matches position=" + position + " in data source");
  }

  @NonNull
  public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    AdapterDelegate<T, VH> delegate = getDelegateForViewType(viewType);
    if (delegate == null) {
      throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
    }

    VH vh = delegate.onCreateViewHolder(parent);
    if (vh == null) {
      throw new NullPointerException("ViewHolder returned from AdapterDelegate "
          + delegate
          + " for ViewType ="
          + viewType
          + " is null!");
    }
    return vh;
  }

  public void onBindViewHolder(@NonNull T items, int position,
                               @NonNull VH viewHolder, List payloads) {

    AdapterDelegate<T, VH> delegate = getDelegateForViewType(viewHolder.getItemViewType());
    if (delegate == null) {
      throw new NullPointerException("No delegate found for item at position = "
          + position
          + " for viewType = "
          + viewHolder.getItemViewType());
    }
    delegate.onBindViewHolder(items, position, viewHolder,
        payloads != null ? payloads : PAYLOADS_EMPTY_LIST);
  }

  public void onBindViewHolder(@NonNull T items, int position,
                               @NonNull VH viewHolder) {
    onBindViewHolder(items, position, viewHolder, PAYLOADS_EMPTY_LIST);
  }

  public void onViewRecycled(@NonNull VH viewHolder) {
    AdapterDelegate<T, VH> delegate = getDelegateForViewType(viewHolder.getItemViewType());
    if (delegate == null) {
      throw new NullPointerException("No delegate found for "
          + viewHolder
          + " for item at position = "
          + viewHolder.getAdapterPosition()
          + " for viewType = "
          + viewHolder.getItemViewType());
    }
    delegate.onViewRecycled(viewHolder);
  }

  public boolean onFailedToRecycleView(@NonNull VH viewHolder) {
    AdapterDelegate<T, VH> delegate = getDelegateForViewType(viewHolder.getItemViewType());
    if (delegate == null) {
      throw new NullPointerException("No delegate found for "
          + viewHolder
          + " for item at position = "
          + viewHolder.getAdapterPosition()
          + " for viewType = "
          + viewHolder.getItemViewType());
    }
    return delegate.onFailedToRecycleView(viewHolder);
  }

  public void onViewAttachedToWindow(VH viewHolder) {
    AdapterDelegate<T, VH> delegate = getDelegateForViewType(viewHolder.getItemViewType());
    if (delegate == null) {
      throw new NullPointerException("No delegate found for "
          + viewHolder
          + " for item at position = "
          + viewHolder.getAdapterPosition()
          + " for viewType = "
          + viewHolder.getItemViewType());
    }
    delegate.onViewAttachedToWindow(viewHolder);
  }

  public void onViewDetachedFromWindow(VH viewHolder) {
    AdapterDelegate<T, VH> delegate = getDelegateForViewType(viewHolder.getItemViewType());
    if (delegate == null) {
      throw new NullPointerException("No delegate found for "
          + viewHolder
          + " for item at position = "
          + viewHolder.getAdapterPosition()
          + " for viewType = "
          + viewHolder.getItemViewType());
    }
    delegate.onViewDetachedFromWindow(viewHolder);
  }

  public void setFallbackDelegate(
      @Nullable AdapterDelegate<T, PowerViewHolder> fallbackDelegate) {
    this.fallbackDelegate = fallbackDelegate;
  }

  public int getViewType(@NonNull AdapterDelegate<T, VH> delegate) {
    if (delegate == null) {
      throw new NullPointerException("Delegate is null");
    }

    int index = delegates.indexOfValue(delegate);
    if (index == -1) {
      return -1;
    }
    return delegates.keyAt(index);
  }

  @Nullable
  public AdapterDelegate<T, VH> getDelegateForViewType(int viewType) {
    return delegates.get(viewType, (AdapterDelegate<T, VH>) fallbackDelegate);
  }

  public AdapterDelegate<T, VH> getDelegateForViewType(@NonNull T items, int position) {

    if (items == null) {
      throw new NullPointerException("Items datasource is null!");
    }

    int delegatesCount = delegates.size();
    for (int i = 0; i < delegatesCount; i++) {
      AdapterDelegate<T, VH> delegate = delegates.valueAt(i);
      if (delegate.isForViewType(items, position)) {
        return delegate;
      }
    }
    return null;
  }

  @Nullable
  public AdapterDelegate<T, PowerViewHolder> getFallbackDelegate() {
    return fallbackDelegate;
  }
}
