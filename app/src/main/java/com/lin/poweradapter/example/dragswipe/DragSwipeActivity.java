package com.lin.poweradapter.example.dragswipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.lin.poweradapter.OnStartDragListener;
import com.lin.poweradapter.SimpleItemTouchHelperCallback;
import com.lin.poweradapter.example.Analog;
import com.lin.poweradapter.example.DatabaseService;
import com.lin.poweradapter.example.RecyclerViewActivity;

/**
 * Created by owp on 2017/4/27.
 */

public class DragSwipeActivity extends RecyclerViewActivity<Analog, DragSwipeAdapter> implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        adapter.setItems(DatabaseService.getSampleData(50));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter, false, true) {
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                swiprefresh.setEnabled(actionState == ItemTouchHelper.ACTION_STATE_IDLE);
            }
        };
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    protected DragSwipeAdapter createAdapter() {
        return new DragSwipeAdapter(this);
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

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
