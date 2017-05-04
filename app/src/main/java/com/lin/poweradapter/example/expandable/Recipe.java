package com.lin.poweradapter.example.expandable;

import com.lin.poweradapter.model.Parent;

import java.util.List;

public class Recipe implements Parent<Ingredient>, IExpand {

    private String mName;
    private List<Ingredient> mIngredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        mName = name;
        mIngredients = ingredients;
    }

    public String getName() {
        return mName;
    }

    @Override
    public List<Ingredient> getChildList() {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public Ingredient getIngredient(int position) {
        return mIngredients.get(position);
    }

    public boolean isVegetarian() {
        for (Ingredient ingredient : mIngredients) {
            if (!ingredient.isVegetarian()) {
                return false;
            }
        }
        return true;
    }
}
