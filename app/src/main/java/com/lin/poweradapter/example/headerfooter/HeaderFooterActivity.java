package com.lin.poweradapter.example.headerfooter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;

/**
 * Created by lin18 on 2017/4/27.
 */

public class HeaderFooterActivity extends RecyclerViewActivity<Analog, HeaderFooterAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        adapter.setItems(DatabaseService.getSampleData(30));
        adapter.setShowHeader(true);
        adapter.setShowFooter(true);
    }

    @NonNull
    @Override
    protected HeaderFooterAdapter createAdapter() {
        return new HeaderFooterAdapter(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        showToast("onItemClick-> position : " + position + " value : " + adapter.getItemExcludeHeader(position).text);
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        showToast("onItemLongClick-> position : " + position + " value : " + adapter.getItemExcludeHeader(position).text);
        return true;
    }
}