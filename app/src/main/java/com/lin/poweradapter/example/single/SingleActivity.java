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
 * Created by lin18 on 2017/4/27.
 */

public class SingleActivity extends RecyclerViewActivity<Analog, AnalogAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                /* emulating https://material-design.storage.googleapis.com/publish/material_v_4/material_ext_publish/0B6Okdz75tqQsck9lUkgxNVZza1U/style_imagery_integration_scale1.png */
                switch (position % 6) {
                    case 5:
                        return 3;
                    case 3:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        configure(gridLayoutManager, new SpaceItemDecoration(this, -1, 10, 10));
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
