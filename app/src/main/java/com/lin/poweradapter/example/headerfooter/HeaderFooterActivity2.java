package com.lin.poweradapter.example.headerfooter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;
import com.lin.poweradapter.model.IMulti;

/**
 * Created by owp on 2017/4/27.
 */

public class HeaderFooterActivity2 extends RecyclerViewActivity<IMulti, HeaderFooter2Adapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        adapter.setItems(DatabaseService.getHeaderFooterData(30));
    }

    @NonNull
    @Override
    protected HeaderFooter2Adapter createAdapter() {
        return new HeaderFooter2Adapter(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        showToast("onItemClick-> position : " + position);
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        showToast("onItemLongClick-> position : " + position);
        return true;
    }
}