package com.lin.poweradapter.example.multi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import com.lin.poweradapter.SpaceItemDecoration;
import com.lin.poweradapter.example.BaseActivity;
import com.lin.poweradapter.example.R;
import com.lin.poweradapter.example.RecyclerViewActivity;
import com.lin.poweradapter.model.IMulti;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lin18 on 2017/4/27.
 */

public class MultiActivity extends RecyclerViewActivity<IMulti, ChatAdapter> {

    @BindView(R.id.et_reply)
    AppCompatEditText etReply;

    private boolean isFromMyself;

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public int getLayoutResource() {
        return R.layout.activity_multi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        configure(layoutManager, new SpaceItemDecoration(this, -1, 10));
    }

    @NonNull
    @Override
    protected ChatAdapter createAdapter() {
        return new ChatAdapter(this);
    }

    @OnClick({R.id.file, R.id.photo, R.id.voice, R.id.iv_send})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.file:
                FileChat file = new FileChat();
                file.isFromMyself = isFromMyself;
                adapter.addItem(file);
                break;

            case R.id.photo:
                PhotoChat photo = new PhotoChat();
                photo.isFromMyself = isFromMyself;
                photo.resId = R.drawable.ic_insert_photo;
                adapter.addItem(photo);
                break;

            case R.id.voice:
                VoiceChat voice = new VoiceChat();
                voice.isFromMyself = isFromMyself;
                adapter.addItem(voice);
                break;

            case R.id.iv_send:
                final String msg = etReply.getText().toString().trim();
                if (TextUtils.isEmpty(msg)) return;
                TextChat text = new TextChat();
                text.isFromMyself = isFromMyself;
                text.message = msg;
                adapter.addItem(text);
                etReply.setText(null);
                break;
        }
        isFromMyself = !isFromMyself;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.getLayoutManager().scrollToPosition(adapter.getItemCount() - 1);
            }
        }, 100);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
    }

    @Override
    public boolean onItemLongClick(View v, int position) {
        return super.onItemLongClick(v, position);
    }
}
