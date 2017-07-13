package com.lin.poweradapter.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.lin.poweradapter.example.animation.AnimationActivity;
import com.lin.poweradapter.example.dragswipe.DragSwipeActivity;
import com.lin.poweradapter.example.expandable.ExpandableActivity;
import com.lin.poweradapter.example.headerfooter.HeaderFooterActivity;
import com.lin.poweradapter.example.headerfooter.HeaderFooterActivity2;
import com.lin.poweradapter.example.loadmore.HLoadMoreActivity;
import com.lin.poweradapter.example.loadmore.LoadMoreActivity;
import com.lin.poweradapter.example.multi.MultiActivity;
import com.lin.poweradapter.example.select.MultiSelectActivity;
import com.lin.poweradapter.example.select.SelectActivity;
import com.lin.poweradapter.example.single.SingleActivity;
import com.lin.poweradapter.example.staggered.StaggeredActivity;
import com.lin.poweradapter.example.stickyheaders.StickyHeadersActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends RecyclerViewActivity<Analog, AnalogAdapter> {

    private String[] mTitles = new String[]{
            SingleActivity.class.getSimpleName(),
            MultiActivity.class.getSimpleName(),
            AnimationActivity.class.getSimpleName(),
            StickyHeadersActivity.class.getSimpleName(),
            StaggeredActivity.class.getSimpleName(),
            SelectActivity.class.getSimpleName(),
            MultiSelectActivity.class.getSimpleName(),
            LoadMoreActivity.class.getSimpleName(),
            HLoadMoreActivity.class.getSimpleName(),
            HeaderFooterActivity.class.getSimpleName(),
            HeaderFooterActivity2.class.getSimpleName(),
            ExpandableActivity.class.getSimpleName(),
            DragSwipeActivity.class.getSimpleName()
    };

    private Class[] mActivities = new Class[]{
            SingleActivity.class,
            MultiActivity.class,
            AnimationActivity.class,
            StickyHeadersActivity.class,
            StaggeredActivity.class,
            SelectActivity.class,
            MultiSelectActivity.class,
            LoadMoreActivity.class,
            HLoadMoreActivity.class,
            HeaderFooterActivity.class,
            HeaderFooterActivity2.class,
            ExpandableActivity.class,
            DragSwipeActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        initData();
    }

    @NonNull
    @Override
    protected AnalogAdapter createAdapter() {
        return new AnalogAdapter(this);
    }

    private void initData() {
        List<Analog> mDataList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            Analog item = new Analog();
            item.text = mTitles[i];
            mDataList.add(item);
        }
        adapter.setItems(mDataList);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        startActivity(new Intent(this, mActivities[position]));
    }
}
