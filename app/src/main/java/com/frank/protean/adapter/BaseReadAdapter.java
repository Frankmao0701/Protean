package com.frank.protean.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frank.protean.R;
import com.frank.protean.bean.ContentPageBean;
import com.frank.protean.wiget.ReadOverViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Adapter
 * Created by Frank on 2017/10/24.
 * 泛型：T Item数据类型
 * 泛型：F Footer viewHolder
 * 泛型：H Head viewHolder
 * 泛型：VH Item viewHolder
 */
public abstract class BaseReadAdapter<VH extends RecyclerView.ViewHolder, F extends ReadOverViewHolder, H extends ReadOverViewHolder> extends RecyclerView.Adapter {

    private List<ContentPageBean> mData;//数据私有 保证数据操作安全
    protected Context mContext;
    protected static final int VIEW_TYPE_ITEM = 0x02;
    protected static final int VIEW_TYPE_FOOTER = 0x03;
    protected static final int VIEW_TYPE_HEAD = 0x04;
    protected boolean hasMore;
    protected boolean hasHeadMore;
    private boolean isHor;

    public void setMode(boolean isHor) {
        this.isHor = isHor;
    }

    protected F onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.layout_readover_view, parent, false);
        F footerViewHolder = (F) new ReadOverViewHolder(view);
        return footerViewHolder;
    }

    protected H onCreateHeadViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.layout_readover_view, parent, false);
        H headViewHolder = (H) new ReadOverViewHolder(view);
        return headViewHolder;
    }

    protected void onBindFooterViewHolder(F holder, int position) {
        if (hasMore) {
            holder.showLoading();
        } else {
            holder.showNoMore();
        }
    }

    protected void onBindHeadViewHolder(H holder, int position) {

    }

    protected int getDataSize() {
        return this.mData == null ? 0 : this.mData.size();
    }

    public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindItemViewHolder(VH holder, int position);


    public BaseReadAdapter(@NonNull Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_HEAD:
                viewHolder = onCreateHeadViewHolder(parent, viewType);
                break;
            case VIEW_TYPE_FOOTER:
                viewHolder = onCreateFooterViewHolder(parent, viewType);
                break;
            case VIEW_TYPE_ITEM:
                viewHolder = onCreateItemViewHolder(parent, viewType);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_HEAD:
                onBindHeadViewHolder((H) holder, position);
                break;
            case VIEW_TYPE_FOOTER:
                onBindFooterViewHolder((F) holder, position);
                break;
            case VIEW_TYPE_ITEM:
                onBindItemViewHolder((VH) holder, position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return getDataSize() + 2;
    }


    @Override
    public int getItemViewType(int position) {
        if (mData != null && mData.size() > 0) {
            if (isFooter(position)) {
                return VIEW_TYPE_FOOTER;
            } else if (isHead(position)) {
                return VIEW_TYPE_HEAD;
            } else {
                return VIEW_TYPE_ITEM;
            }
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ContentPageBean getItem(int position) {
        if (this.mData != null && this.mData.size() >= position) {
            return this.mData.get(position - 1);
        }
        return null;
    }

    public void setData(@NonNull List<ContentPageBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addData(@NonNull List<ContentPageBean> data) {
        if (this.mData != null) {
            this.mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addHeadData(@NonNull List<ContentPageBean> data) {
        if (this.mData != null) {
            this.mData.addAll(0, data);
            notifyDataSetChanged();
        }
    }

    public List<ContentPageBean> getData() {
        return this.mData;
    }

    public void removeDataAll(List<String> data) {
        if (this.mData != null) {
            this.mData.removeAll(data);
            notifyDataSetChanged();
        }
    }

    public int indexOf(Object obj) {
        int index = -1;
        if (this.mData != null) {
            index = this.mData.indexOf(obj);
        }
        return index;
    }

    public void hasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void hasHeadMore(boolean hasHeadMore) {
        this.hasHeadMore = hasHeadMore;
    }

    public boolean isFooter(int position) {
        return position == (getItemCount() - 1) && getItemCount() > 0;
    }

    private boolean isHead(int position) {
        return position == 0 && getItemCount() > 0;
    }

    public void addHead(ContentPageBean pageBean) {
        if (this.mData != null) {
            mData.add(0, pageBean);
            notifyItemInserted(0);
            notifyItemRangeChanged(0, mData.size());
        }
    }

    public void addBottomLock(ContentPageBean pageBean) {
        if (this.mData != null) {
            mData.add(pageBean);
            notifyDataSetChanged();
        }
    }
}
