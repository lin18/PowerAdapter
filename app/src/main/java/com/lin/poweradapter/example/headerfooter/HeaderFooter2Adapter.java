package com.lin.poweradapter.example.headerfooter;

import android.support.annotation.Nullable;

import com.lin.poweradapter.PowerViewHolder;
import com.lin.poweradapter.MultiAdapter;
import com.lin.poweradapter.model.IMulti;

/**
 * Created by lin18 on 2017/5/2.
 */

public class HeaderFooter2Adapter extends MultiAdapter<IMulti, PowerViewHolder> {

    public HeaderFooter2Adapter(@Nullable Object listener) {
        super(listener);
        delegatesManager.addDelegate(new AnalogAdapterDelegate());
        delegatesManager.addDelegate(new headerAdapterDelegate());
        delegatesManager.addDelegate(new FooterAdapterDelegate());
    }

}
