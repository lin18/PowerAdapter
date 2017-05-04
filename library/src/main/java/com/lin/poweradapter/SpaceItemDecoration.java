package com.lin.poweradapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mSectionOffsetV;
    private int mSectionOffsetH;
    private boolean mDrawOver = true, withOffset = false;

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public SpaceItemDecoration(Context context) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        mDivider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    public SpaceItemDecoration(@NonNull Context context, @DrawableRes int resId) {
        this(context, resId, 0);
    }

    public SpaceItemDecoration(@NonNull Context context, @DrawableRes int resId,
                               @IntRange(from = 0) int sectionOffset) {
        this(context, resId, sectionOffset, 0);
    }

    public SpaceItemDecoration(@NonNull Context context, @DrawableRes int resId,
                               @IntRange(from = 0) int sectionOffsetV, @IntRange(from = 0) int sectionOffsetH) {
        if (resId > 0) mDivider = ContextCompat.getDrawable(context, resId);
        mSectionOffsetV = (int) (context.getResources().getDisplayMetrics().density * sectionOffsetV);
        mSectionOffsetH = (int) (context.getResources().getDisplayMetrics().density * sectionOffsetH);
    }

    public SpaceItemDecoration withDrawOver(boolean drawOver) {
        this.mDrawOver = drawOver;
        return this;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDivider != null && !mDrawOver) {
            draw(c, parent);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDivider != null && mDrawOver) {
            draw(c, parent);
        }
    }

    private void draw(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + (mDivider.getIntrinsicHeight() <= 0 ? 1 : mDivider.getIntrinsicHeight());

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public SpaceItemDecoration withOffset(boolean withOffset) {
        this.withOffset = withOffset;
        return this;
    }

    /**
     *
     */
    @SuppressWarnings({"ConstantConditions", "unchecked", "SuspiciousNameCombination"})
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (getOrientation(recyclerView.getLayoutManager()) == RecyclerView.VERTICAL) {
            outRect.set(mSectionOffsetH, 0, mSectionOffsetH, mSectionOffsetV);
        } else {
            outRect.set(0, 0, mSectionOffsetV, 0);
        }
    }

    public static int getOrientation(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        }
        return OrientationHelper.HORIZONTAL;
    }

}