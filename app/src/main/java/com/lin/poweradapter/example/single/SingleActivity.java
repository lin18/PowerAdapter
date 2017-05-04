package com.lin.poweradapter.example.single;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lin.poweradapter.SpaceItemDecoration;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.AnalogAdapter;
import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;

/**
 * Created by owp on 2017/4/27.
 */

public class SingleActivity extends RecyclerViewActivity<Analog, AnalogAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure(new GridLayoutManager(this, 2), new SpaceItemDecoration(this, -1, 10, 10));
        adapter.setItems(DatabaseService.getSampleData(300));
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 30);
    }

    @NonNull
    @Override
    protected AnalogAdapter createAdapter() {
        return new AnalogAdapter(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        showToast("onItemClick-> position : " + position + " value : " + adapter.getItem(position).text);
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        showToast("onItemLongClick-> position : " + position + " value : " + adapter.getItem(position).text);
        return true;
    }
}
