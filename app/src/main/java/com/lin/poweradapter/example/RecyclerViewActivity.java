package com.lin.poweradapter.example;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.poweradapter.SingleAdapter;
import com.lin.poweradapter.SpaceItemDecoration;
import com.lin.poweradapter.example.util.Utils;

import butterknife.BindView;

/**
 * Created by lin18 on 2017/4/28.
 */

public abstract class RecyclerViewActivity<E, B extends SingleAdapter> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        SingleAdapter.OnItemClickListener, SingleAdapter.OnItemLongClickListener {

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.swiprefresh)
    protected SwipeRefreshLayout swiprefresh;
    @Nullable
    protected ViewStub errorView;
    @Nullable
    protected TextView textError;
    @Nullable
    protected ViewStub emptyView;
    @Nullable
    protected ImageView icon;
    @Nullable
    protected TextView empty;
    @Nullable
    protected ViewStub progressView;

    @NonNull
    protected B adapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.swipeback_stack_to_front,
                R.anim.swipeback_stack_right_out);
    }

    protected void configure() {
        configure(new SpaceItemDecoration(this, R.drawable.divider));
    }

    protected void configure(RecyclerView.ItemDecoration decor) {
        configure(new LinearLayoutManager(this), decor);
    }

    protected void configure(RecyclerView.LayoutManager layout) {
        configure(layout, new SpaceItemDecoration(this, R.drawable.divider));
    }

    protected void configure(RecyclerView.LayoutManager layout, RecyclerView.ItemDecoration decor) {
        setOnRefreshListener();
        initRecyclerView(layout, decor);
        adapter = createAdapter();
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    protected abstract B createAdapter();

    protected void initRecyclerView() {
        initRecyclerView(new SpaceItemDecoration(this, R.drawable.divider));
    }

    protected void initRecyclerView(RecyclerView.ItemDecoration decor) {
        initRecyclerView(new LinearLayoutManager(this), decor);
    }

    protected void initRecyclerView(RecyclerView.LayoutManager layout, RecyclerView.ItemDecoration decor) {
        recyclerView.setLayoutManager(layout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(decor);
    }

    protected void setOnRefreshListener() {
        if (swiprefresh != null)
            swiprefresh.setOnRefreshListener(this);
    }

    protected void loadData() {
        setRefreshing(false);
    }

    protected void setRefreshing(boolean refreshing) {
        if (swiprefresh != null)
            swiprefresh.setRefreshing(refreshing);
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onItemClick(View v, int position) {
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        return false;
    }

    protected void refreshWithSwipe() {
        adapter.clear();
        setRefreshing(true);
        onRefresh();
    }

    protected void refreshWithProgress() {
        adapter.clear();
        if (progressView == null) {
            progressView = (ViewStub) findViewById(R.id.progress_view);
            progressView.inflate();
        }
        showWithFadeIn(progressView).hide(recyclerView);
        onRefresh();
    }

    protected RecyclerViewActivity<E, B> showRecyclerView() {
        showWithFadeIn(recyclerView).hide(emptyView).hide(emptyView);
        return this;
    }

    protected RecyclerViewActivity<E, B> showEmpty() {
        return showEmpty(null, null);
    }

    protected RecyclerViewActivity<E, B> showEmpty(@StringRes int resid) {
        return showEmpty(null, getString(resid));
    }

    protected RecyclerViewActivity<E, B> showEmpty(@Nullable Drawable drawable, CharSequence text) {
        if (emptyView == null) {
            emptyView = (ViewStub) findViewById(R.id.empty_view);
            emptyView.inflate();
        }
        if (icon == null)
            icon = (ImageView) emptyView.findViewById(android.R.id.icon);
        icon.setImageDrawable(drawable);
        if (empty == null)
            empty = (TextView) emptyView.findViewById(android.R.id.empty);
        empty.setText(text);
        showWithFadeIn(emptyView).hide(recyclerView);
        return this;
    }

    protected RecyclerViewActivity<E, B> showError() {
        return showError(getString(R.string.text_error));
    }

    protected RecyclerViewActivity<E, B> showError(CharSequence text) {
        if (errorView == null) {
            errorView = (ViewStub) findViewById(R.id.error_view);
            errorView.inflate();
        }
        if (textError == null)
            textError = (TextView) errorView.findViewById(R.id.text_error);
        textError.setText(text);
        showWithFadeIn(errorView).hide(recyclerView);
        return this;
    }

    protected RecyclerViewActivity<E, B> showWithFadeIn(final View view) {
        if (view != null) {
            show(fadeIn(view));
        }
        return this;
    }

    protected RecyclerViewActivity<E, B> show(final View view) {
        if (view != null)
            view.setVisibility(View.VISIBLE);
        return this;
    }

    protected RecyclerViewActivity<E, B> hide(final View view) {
        if (view != null)
            view.setVisibility(View.GONE);
        return this;
    }

    public View fadeIn(@NonNull View view) {
        return Utils.fadeIn(this, view);
    }

    protected void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
