package com.lin.poweradapter.example.stickyheaders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gjiazhe.wavesidebar.WaveSideBar;
import com.google.gson.reflect.TypeToken;
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.RecyclerViewActivity;
import com.lin.poweradapter.example.util.JSONUtils;
import com.lin.poweradapter.example.util.Utils;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by owp on 2017/4/27.
 */

public class StickyHeadersActivity extends RecyclerViewActivity<City, StickyHeadersAdapter> {

    @BindView(R.id.side_bar)
    WaveSideBar sideBar;

    StickyRecyclerHeadersDecoration headersDecor;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_stickyheaders;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                int pos = adapter.getLetterPosition(letter);

                if (pos != -1) {
                    recyclerView.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) recyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });

        configure();
        headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });
        loadData();
    }

    @NonNull
    @Override
    protected StickyHeadersAdapter createAdapter() {
        return new StickyHeadersAdapter(this);
    }

    @Override
    protected void loadData() {
        new AsyncTask<Void, Void, List<City>>() {

            @Override
            protected List<City> doInBackground(Void... params) {
                final List<City> list = JSONUtils.toList(Utils.readFromAssets(StickyHeadersActivity.this, "cities.json"), new TypeToken<ArrayList<City>>() {});
                Collections.sort(list, new LetterComparator());
                return list;
            }

            @Override
            protected void onPostExecute(List<City> cities) {
                super.onPostExecute(cities);
                if (isFinishing()) return;
                adapter.setItems(cities);
                setRefreshing(false);
            }
        }.execute();
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        showToast("onItemClick : " + adapter.getItem(position).name);
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        showToast("onItemLongClick : " + adapter.getItem(position).name);
        return true;
    }
}
