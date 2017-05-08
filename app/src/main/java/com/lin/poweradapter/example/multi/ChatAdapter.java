package com.lin.poweradapter.example.multi;

import android.support.annotation.Nullable;

import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.DefaultAdapterDelegate;
import com.lin.poweradapter.MultiAdapter;

/**
 * Created by lin18 on 2017/5/2.
 */

public class ChatAdapter extends MultiAdapter<Chat, PowerViewHolder> {

    public ChatAdapter(@Nullable Object listener) {
        super(listener);
        delegatesManager.addDelegate(new LeftTextAdapterDelegate());
        delegatesManager.addDelegate(new RightTextAdapterDelegate());
        delegatesManager.addDelegate(new LeftFileAdapterDelegate());
        delegatesManager.addDelegate(new RightFileAdapterDelegate());
        delegatesManager.addDelegate(new LeftPhotoAdapterDelegate());
        delegatesManager.addDelegate(new RightPhotoAdapterDelegate());
        delegatesManager.addDelegate(new LeftVoiceAdapterDelegate());
        delegatesManager.addDelegate(new RightVoiceAdapterDelegate());
        delegatesManager.setFallbackDelegate(new DefaultAdapterDelegate());
    }

}
