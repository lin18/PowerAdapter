package com.lin.poweradapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lin.poweradapter.model.Parent;

/**
 *
 * Created by lin18 on 2017/4/26.
 */

public class ParentExpandViewHolder<P extends Parent<C>, C> extends PowerViewHolder implements View.OnClickListener {

    @Nullable
    private ParentViewHolderExpandCollapseListener mParentViewHolderExpandCollapseListener;
    private boolean mExpanded;
    P mParent;
    ExpandableAdapter mExpandableAdapter;

    public ParentExpandViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
        super(parent, layoutResId);
        mExpanded = false;
    }

    public ParentExpandViewHolder(View itemView) {
        super(itemView);
        mExpanded = false;
    }

    public P getParent() {
        return mParent;
    }

    public int getParentAdapterPosition() {
        int flatPosition = getAdapterPosition();
        if (flatPosition == RecyclerView.NO_POSITION) {
            return flatPosition;
        }

        return mExpandableAdapter.getNearestParentPosition(flatPosition);
    }

    public void setMainItemClickToExpand() {
        getContentView().setOnClickListener(this);
    }

    public boolean isExpanded() {
        return mExpanded;
    }

    public void setExpanded(boolean expanded) {
        mExpanded = expanded;
    }

    public void onExpansionToggled(boolean expanded) {

    }

    void setParentViewHolderExpandCollapseListener(ParentViewHolderExpandCollapseListener parentViewHolderExpandCollapseListener) {
        mParentViewHolderExpandCollapseListener = parentViewHolderExpandCollapseListener;
    }

    @Override
    public void onClick(View v) {
        if (mExpanded) {
            collapseView();
        } else {
            expandView();
        }
    }

    public boolean shouldItemViewClickToggleExpansion() {
        return true;
    }

    protected void expandView() {
        setExpanded(true);
        onExpansionToggled(false);

        if (mParentViewHolderExpandCollapseListener != null) {
            mParentViewHolderExpandCollapseListener.onParentExpanded(getAdapterPosition());
        }
    }

    protected void collapseView() {
        setExpanded(false);
        onExpansionToggled(true);

        if (mParentViewHolderExpandCollapseListener != null) {
            mParentViewHolderExpandCollapseListener.onParentCollapsed(getAdapterPosition());
        }
    }

    interface ParentViewHolderExpandCollapseListener {

        void onParentExpanded(int flatParentPosition);

        void onParentCollapsed(int flatParentPosition);
    }

}
