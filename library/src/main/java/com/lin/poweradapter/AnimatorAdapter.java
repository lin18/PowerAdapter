package com.lin.poweradapter;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by lin18 on 2017/5/3.
 */

public abstract class AnimatorAdapter<VH extends RecyclerView.ViewHolder> extends SelectableAdapter<VH> {

    private int mDuration = 300;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mLastPosition = -1;

    private boolean isFirstOnly = true;

    @Override
    public void onBindViewHolder(VH holder, int position) {
        super.onBindViewHolder(holder, position);
        final Animator[] animators = getAnimators(holder.itemView);
        if (animators != null) {
            int adapterPosition = holder.getAdapterPosition();
            if (!isFirstOnly || adapterPosition > mLastPosition) {
                for (Animator anim : animators) {
                    anim.setDuration(mDuration).start();
                    anim.setInterpolator(mInterpolator);
                }
                mLastPosition = adapterPosition;
            } else {
                ViewHelper.clear(holder.itemView);
            }
        }
    }

    public void setFirstOnly(boolean firstOnly) {
        isFirstOnly = firstOnly;
    }

    protected Animator[] getAnimators(View view) {
        return null;
    }
}