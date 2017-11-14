# PowerAdapter
Adapter for RecyclerView

# Demo apk
[Demo apk](https://raw.githubusercontent.com/lin18/PowerAdapter/master/app-debug.apk)
<br>
<br>
<br>![](https://github.com/lin18/PowerAdapter/blob/master/screenshots/demo.gif?raw=true)

# Use
```gradle
compile 'com.lin:poweradapter:1.5@aar'
```

- ### Single 一种Item([SingleActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/single/SingleActivity.java))
``` Java
public class AnalogAdapter extends SingleAdapter<Analog, AnalogAdapter.ChildViewHolder> {

    public AnalogAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return true;
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.analog_item);
    }

    @Override
    public void onBindVHolder(AnalogAdapter.ChildViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Analog analog = getItem(position);
        holder.title.setText(analog.text);
        if (analog.resId > 0) {
            holder.icon.setImageResource(analog.resId);
            holder.icon.setVisibility(View.VISIBLE);
        } else {
            holder.icon.setVisibility(View.GONE);
        }

    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;
        @BindView(android.R.id.title)
        TextView title;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }
}
单击：implements SingleAdapter.OnItemClickListener
@Override
public void onItemClick(View v, int position) {
    Toast.makeText(this, "onItemClick-> position : " + position + " value : " + adapter.getItem(position).text, Toast.LENGTH_SHORT).show();
}
双击：implements SingleAdapter.OnItemLongClickListener
@Override
public boolean onItemLongClick(View v, int position) {
    Toast.makeText(this, "onItemLongClick-> position : " + position + " value : " + adapter.getItem(position).text, Toast.LENGTH_SHORT).show();
    return true;
}
```
- ### Multi 多种Item([MultiActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/multi/MultiActivity.java))
``` Java
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
        delegatesManager.setFallbackDelegate(new DefaultAdapterDelegate<Chat>());
    }

}

public class LeftTextAdapterDelegate extends AdapterDelegate<Chat, PowerViewHolder> {

    @Override
    protected boolean isForViewType(@NonNull Chat items, int position) {
        return items instanceof TextChat && !items.isFromMyself;
    }

    @NonNull
    @Override
    protected ChildViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.left_text_chat_item);
    }

    @Override
    protected void onBindViewHolder(@NonNull Chat item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        ChildViewHolder vh = (ChildViewHolder) holder;
        TextChat textChat = (TextChat) item;
        vh.textView.setText(textChat.message);
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.text_view)
        TextView textView;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
```
其它几个类同。
- ### Animation 动画([AnimationActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/animation/AnimationActivity.java))
<br>使用的是第三方库，看例子
- ### Sticky Headers 粘性头部 ([StickyHeadersActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/stickyheaders/StickyHeadersActivity.java))
<br>使用的是第三方库，看例子
- ### Staggered 瀑布流([StaggeredActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/staggered/StaggeredActivity.java))
<br>使用StaggeredGridLayoutManager，看例子
- ### Single Select 单选([SelectActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/select/SelectActivity.java))
``` Java
adapter重写
@Override
public boolean isSelectable(@IntRange(from = 0) int position) {
    return true;
}
adapter.setMode(MODE_SINGLE);
```
- ### Multi Select 多选([MultiSelectActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/select/MultiSelectActivity.java))
``` Java
adapter.setMode(MODE_MULTI);
```
- ### Load More 加载更多([LoadMoreActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/loadmore/LoadMoreActivity.java))
``` Java
listener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerView.getLayoutManager(), adapter) {
    @Override
    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        count++;
        if (count < 6) {
            adapter.setLoadMore(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.addAll(DatabaseService.getSampleData(30));
                    adapter.setLoadMore(true);
                }
            }, 2000);
        } else {
            adapter.setLoadMore(false);
        }
    }
};
recyclerView.addOnScrollListener(listener);
adapter.setItems(DatabaseService.getSampleData(40));
adapter.setLoadMore(true);
重置
listener.resetState();
adapter.setItems(DatabaseService.getSampleData(30));
adapter.setLoadMore(true);
```
- ### Header And Footer 头部和尾部([HeaderFooterActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/headerfooter/HeaderFooterActivity.java))
``` Java
public class HeaderFooterAdapter extends SingleAdapter<Analog, BaseViewHolder> {

    public HeaderFooterAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public boolean isSelectable(@IntRange(from = 0) int position) {
        return true;
    }

    @Override
    public BaseViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.analog_item);
    }

    @Override
    protected BaseViewHolder getHeaderHolder(ViewGroup parent) {
        return new HeaderViewHolder(parent, R.layout.header_layout);
    }

    @Override
    protected BaseViewHolder getFooterHolder(ViewGroup parent) {
        return new FooterViewHolder(parent, R.layout.footer_layout);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == HEADER) {
            final HeaderViewHolder vh = (HeaderViewHolder) holder;
            vh.icon.setImageResource(R.drawable.ic_airplay);
        } else if (getItemViewType(position) == FOOTER) {
            final FooterViewHolder vh = (FooterViewHolder) holder;
            vh.icon.setImageResource(R.drawable.ic_visibility);
        }
    }

    @Override
    public void onBindVHolder(BaseViewHolder holder, int position) {
        final Analog analog = getItemExcludeHeader(position);
        final ChildViewHolder vh = (ChildViewHolder) holder;
        vh.title.setText(analog.text);
        if (analog.resId > 0) {
            vh.icon.setImageResource(analog.resId);
            vh.icon.setVisibility(View.VISIBLE);
        } else {
            vh.icon.setVisibility(View.GONE);
        }

    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.icon)
        ImageView icon;
        @BindView(android.R.id.title)
        TextView title;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }

    static class HeaderViewHolder extends BaseViewHolder {
        @BindView(android.R.id.icon)
        ImageView icon;
        HeaderViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

    static class FooterViewHolder extends BaseViewHolder {
        @BindView(android.R.id.icon)
        ImageView icon;
        FooterViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }
}
```
- ### Header And Footer 头部和尾部([HeaderFooterActivity2](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/headerfooter/HeaderFooterActivity2.java))
<br>和多个复杂的写法一样
- ### Expandable 分组的伸缩([ExpandableActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/expandable/ExpandableActivity.java))
<br>分组的伸缩写法和多个复杂的写法一样，不过item中的ViewHolder继承有点变化，如下：
<br>展开的头部继承ParentExpandViewHolder，可以伸缩的部分继承ChildExpandViewHolder。
- ### Drag And Swipe 拖拽和滑动删除([DragSwipeActivity](https://github.com/lin18/PowerAdapter/blob/master/app/src/main/java/com/lin/poweradapter/example/dragswipe/DragSwipeActivity.java))
<br>拖拽和滑动删除和一种item的一样，ViewHolder继承的是DragSwipeViewHolder，
``` Java
ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter, false, true) {
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        swiprefresh.setEnabled(actionState == ItemTouchHelper.ACTION_STATE_IDLE);
    }
};
mItemTouchHelper = new ItemTouchHelper(callback);
mItemTouchHelper.attachToRecyclerView(recyclerView);
implements OnStartDragListener并
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
```
# Thanks
[AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)
<br>[FlexibleAdapter](https://github.com/davideas/FlexibleAdapter)
<br>[recyclerview-animators](https://github.com/wasabeef/recyclerview-animators)
<br>[Android-ItemTouchHelper-Demo](https://github.com/iPaulPro/Android-ItemTouchHelper-Demo)
<br>[expandable-recycler-view](https://github.com/bignerdranch/expandable-recycler-view)

# License
```
Copyright 2017 lin18

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```