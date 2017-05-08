package com.lin.poweradapter.example.expandable;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.ParentExpandViewHolder;
import com.lin.poweradapter.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lin18 on 2017/4/25.
 */

public class RecipeAdapterDelegate extends AdapterDelegate<IExpand, PowerViewHolder> {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @Override
    protected boolean isForViewType(@NonNull IExpand item, int position) {
        return item instanceof Recipe;
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.vegetarian_recipe_view);
    }

    @Override
    protected void onBindViewHolder(@NonNull IExpand item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        ChildViewHolder vh = (ChildViewHolder) holder;
        Recipe recipe = (Recipe) item;

        vh.textView.setText(recipe.getName());
    }

    static class ChildViewHolder extends ParentExpandViewHolder {

        Unbinder unbinder;

        @BindView(R.id.recipe_textview)
        TextView textView;
        @BindView(R.id.arrow_expand_imageview)
        ImageView arrow;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            this(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
        }

        public ChildViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);
            arrow.setRotation(expanded ? ROTATED_POSITION : INITIAL_POSITION);
        }

        @Override
        public void onExpansionToggled(boolean expanded) {
            super.onExpansionToggled(expanded);
            RotateAnimation rotateAnimation = new RotateAnimation(expanded ? ROTATED_POSITION : -1 * ROTATED_POSITION,
                    INITIAL_POSITION,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            arrow.startAnimation(rotateAnimation);
        }

    }
}
