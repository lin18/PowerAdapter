package com.lin.poweradapter.example.animation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;

import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

/**
 * Created by owp on 2017/4/27.
 */

public class AnimationActivity extends RecyclerViewActivity<Analog, AnimationAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();

        recyclerView.setItemAnimator(new ScaleInBottomAnimator());

        adapter.setItems(DatabaseService.getSampleData(300));
    }

    @NonNull
    @Override
    protected AnimationAdapter createAdapter() {
        return new AnimationAdapter(this);
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