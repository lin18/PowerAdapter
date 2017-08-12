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

    int count;

    EndlessRecyclerViewScrollListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        listener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerView.getLayoutManager(), adapter) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                count++;
                if (count < 4) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addAll(DatabaseService.getSampleData(30));
                        }
                    }, 2000);
                } else {
                    adapter.setLoadMore(false);
                }
            }
        };
        recyclerView.addOnScrollListener(listener);
        adapter.setItems(DatabaseService.getSampleData(40));
        adapter.setLoadMore(true);
    }

    @NonNull
    @Override
    protected LoadMoreAdapter createAdapter() {
        return new LoadMoreAdapter(this);
    }

    @Override
    public void onRefresh() {
        count = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.resetState();
                adapter.replaceAll(DatabaseService.getSampleData(30));
                adapter.setLoadMore(true);
                setRefreshing(false);
            }
        }, 2000);
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