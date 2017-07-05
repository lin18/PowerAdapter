package com.lin.poweradapter.example.loadmore;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lin.poweradapter.EndlessRecyclerViewScrollListener;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;

/**
 * Created by lin18 on 2017/4/27.
 */

public class LoadMoreActivity extends RecyclerViewActivity<Analog, LoadMoreAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        adapter.setItems(DatabaseService.getSampleData(40));
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerView.getLayoutManager(), adapter) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(DatabaseService.getSampleData(30));
                    }
                }, 2000);
            }
        });
        adapter.setLoadMore(true);
    }

    @NonNull
    @Override
    protected LoadMoreAdapter createAdapter() {
        return new LoadMoreAdapter(this);
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