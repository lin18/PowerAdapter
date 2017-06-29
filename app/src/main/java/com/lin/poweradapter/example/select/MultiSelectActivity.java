package com.lin.poweradapter.example.select;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;

import static com.lin.poweradapter.SelectableAdapter.MODE_MULTI;

/**
 * Created by lin18 on 2017/4/27.
 */

public class MultiSelectActivity extends RecyclerViewActivity<Analog, SelectAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        adapter.setItems(DatabaseService.getSampleData(300));
        adapter.setMode(MODE_MULTI);
    }

    @NonNull
    @Override
    protected SelectAdapter createAdapter() {
        return new SelectAdapter(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        adapter.toggleSelection(position);
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        showToast("onItemLongClick-> position : " + position + " value : " + adapter.getItem(position).text);
        return true;
    }
}