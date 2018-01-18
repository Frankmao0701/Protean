package com.frank.protean.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frank.protean.R;
import com.frank.protean.ScreenUtils;
import com.frank.protean.activity.PayActivity;
import com.frank.protean.bean.ContentPageBean;
import com.squareup.picasso.Picasso;

/**
 * Created by Frankmao on 2017/8/22.
 */

public class ContentAdapter extends BaseReadAdapter<ContentAdapter.ListViewHolder,ContentAdapter.LockViewHolder,ContentAdapter.LoadingViewHolder> {
    private OnBackClickListener onBackClickListener;
    public ContentAdapter(@NonNull Context context) {
        super(context);
    }
    public void setOnBackClickListener(OnBackClickListener onBackClickListener){
        this.onBackClickListener = onBackClickListener;
    }

    @Override
    public ListViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.layout_item_content, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ListViewHolder holder, int position) {
//        Log.e("ContentAdapter", position + "");
        ContentPageBean pageBean = getItem(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = ScreenUtils.getScreenWidth(mContext) - 2;
        holder.img_content.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Picasso.with(mContext).load(pageBean.url).into(holder.img_content);
//        String text = getItem(position);
//        holder.tv.setText(text);
    }

    @Override
    public LockViewHolder onCreateLockViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.layout_item_lock, parent, false);
        return new LockViewHolder(view);
    }

    @Override
    public void onBindLockViewHolder(LockViewHolder holder, final int position) {
        holder.tv_title.setText(getItem(position).title);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackClickListener.onBack(getItem(position).id);
                }
            });
    }

    @Override
    public LoadingViewHolder onCreateLoadingViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.layout_item_loading, parent, false);
        return new LoadingViewHolder(view);
    }

    @Override
    public void onBindLoadingViewHolder(LoadingViewHolder holder, int position) {

    }


    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView img_content;

        public ListViewHolder(View itemView) {
            super(itemView);
            img_content = (ImageView) itemView.findViewById(R.id.img_content);
        }
    }
    public static class LockViewHolder extends RecyclerView.ViewHolder {
        Button button;
        TextView tv_title;

        public LockViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
    public static class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
    public interface OnBackClickListener{
        void onBack(String id);
    }

}
