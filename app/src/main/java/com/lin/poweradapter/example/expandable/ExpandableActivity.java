package com.lin.poweradapter.example.expandable;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.SpaceItemDecoration;
import com.lin.poweradapter.example.BaseActivity;
import com.lin.poweradapter.example.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by owp on 2017/4/25.
 */

public class ExpandableActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SingleAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;

    ExpandableItemAdapter mAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_expandable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        swiprefresh.setOnRefreshListener(this);



        Ingredient beef = new Ingredient("beef", false);
        Ingredient cheese = new Ingredient("cheese", true);
        Ingredient salsa = new Ingredient("salsa", true);
        Ingredient tortilla = new Ingredient("tortilla", true);
        Ingredient ketchup = new Ingredient("ketchup", true);
        Ingredient bun = new Ingredient("bun", true);

        Recipe taco = new Recipe("taco", Arrays.asList(beef, cheese, salsa));
        Recipe quesadilla = new Recipe("quesadilla", Arrays.asList(cheese, tortilla));
        Recipe burger = new Recipe("burger", Arrays.asList(beef, cheese, ketchup, bun));
        final List<IExpand> recipes = new ArrayList<>();
        recipes.add(taco);
        recipes.add(quesadilla);
        recipes.add(burger);
//        mAdapter.setItems(recipes);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ExpandableItemAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SpaceItemDecoration(this, R.drawable.divider));

        mAdapter.setItems(recipes);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, ((Ingredient)mAdapter.getItem(position)).getName()+"=="+position, Toast.LENGTH_SHORT).show();
    }
}
