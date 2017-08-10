package com.lin.poweradapter.example.expandable;

import android.support.annotation.Nullable;

import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.DefaultAdapterDelegate;
import com.lin.poweradapter.ExpandableAdapter;

/**
 * Created by lin18 on 2017/4/25.
 */

public class ExpandableItemAdapter extends ExpandableAdapter<IExpand, Recipe, Ingredient, PowerViewHolder>{

    public ExpandableItemAdapter(@Nullable Object listener) {
        super(listener);
        delegatesManager.addDelegate(new IngredientAdapterDelegate());
        delegatesManager.addDelegate(new RecipeAdapterDelegate());
        delegatesManager.setFallbackDelegate(new DefaultAdapterDelegate<IExpand>());
    }

}
