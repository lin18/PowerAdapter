package com.lin.poweradapter.example.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by owp on 2017/5/2.
 */

public class AnimationAdapter extends SingleAdapter<Analog, AnimationAdapter.ChildViewHolder> {

    public AnimationAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    protected boolean isUseAnimation() {
        return true;
    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[] {
                ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
        };
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.analog_item);
    }

    @Override
    public void onBindVHolder(AnimationAdapter.ChildViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Analog analog = getItem(position);
        holder.title.setText(analog.text);
        if (analog.resId > 0) {
            holder.icon.setImageResource(analog.resId);
            holder.icon.setVisibility(View.VISIBLE);
        } else {
            holder.icon.setVisibility(View.GONE);
        }

    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;
        @BindView(android.R.id.title)
        TextView title;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }
}