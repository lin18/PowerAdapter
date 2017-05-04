package com.lin.poweradapter.example.expandable;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.ChildExpandViewHolder;
import com.lin.poweradapter.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by owp on 2017/4/25.
 */

public class IngredientAdapterDelegate extends AdapterDelegate<IExpand, PowerViewHolder> {

    @Override
    protected boolean isForViewType(@NonNull IExpand item, int position) {
        return item instanceof Ingredient;
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.vegetarian_ingredient_view);
    }

    @Override
    protected void onBindViewHolder(@NonNull IExpand item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        ChildViewHolder vh = (ChildViewHolder) holder;
        Ingredient ingredient = (Ingredient) item;
        vh.textView.setText(ingredient.getName());
    }

    static class ChildViewHolder extends ChildExpandViewHolder {

        Unbinder unbinder;

        @BindView(R.id.ingredient_textview)
        TextView textView;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            this(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
        }

        public ChildViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
