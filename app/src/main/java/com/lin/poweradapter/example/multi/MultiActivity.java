package com.lin.poweradapter.example.multi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatEditText;
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
 * Created by owp on 2017/4/27.
 */

public class MultiActivity extends RecyclerViewActivity<IMulti, ChatAdapter> {

    @BindView(R.id.et_reply)
    AppCompatEditText etReply;

    private boolean isFromMyself;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_multi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        configure(new SpaceItemDecoration(this, -1, 10));
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
                adapter.add(file);
                break;

            case R.id.photo:
                PhotoChat photo = new PhotoChat();
                photo.isFromMyself = isFromMyself;
                photo.resId = R.drawable.ic_insert_photo;
                adapter.add(photo);
                break;

            case R.id.voice:
                VoiceChat voice = new VoiceChat();
                voice.isFromMyself = isFromMyself;
                adapter.add(voice);
                break;

            case R.id.iv_send:
                final String msg = etReply.getText().toString().trim();
                if (TextUtils.isEmpty(msg)) return;
                TextChat text = new TextChat();
                text.isFromMyself = isFromMyself;
                text.message = msg;
                adapter.add(text);
                etReply.setText(null);
                break;
        }
        isFromMyself = !isFromMyself;
    }
}
