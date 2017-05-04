package com.lin.poweradapter.example.staggered;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.lin.poweradapter.SpaceItemDecoration;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;

/**
 * Created by owp on 2017/4/27.
 */

public class StaggeredActivity extends RecyclerViewActivity<Analog, StaggeredAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL), new SpaceItemDecoration(this, -1, 10));
        adapter.setItems(DatabaseService.getStaggeredData(50));
    }

    @NonNull
    @Override
    protected StaggeredAdapter createAdapter() {
        return new StaggeredAdapter(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        showToast("onItemClick-> position : " + position + " value : " + adapter.getItem(position).title);
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        showToast("onItemLongClick-> position : " + position + " value : " + adapter.getItem(position).title);
        return true;
    }
}