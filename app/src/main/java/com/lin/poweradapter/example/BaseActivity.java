package com.lin.poweradapter.example;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lin18 on 2017/4/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutResource();
        if (layoutResId != 0) {
            setContentView(layoutResId);
            unbinder = ButterKnife.bind(this);

            View v = findViewById(R.id.toolbar);
            if (v != null) {
                toolbar = (Toolbar) v;
                setSupportActionBar(toolbar);

            }
        }

    }

    @LayoutRes
    public abstract int getLayoutResource();

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroy();
    }

}
