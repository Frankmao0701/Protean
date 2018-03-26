package com.frank.protean.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frank.protean.R;
import com.frank.protean.bean.ContentPageBean;

import java.util.List;

/**
 * Created by Frankmao on 2018/1/29.
 */

public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private SparseArray<List<ContentPageBean>> sparseArray;

    public FirstAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(SparseArray<List<ContentPageBean>> sparseArray) {
        this.sparseArray = sparseArray;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_first, parent, false);
        return new FirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FirstViewHolder firstViewHolder = (FirstViewHolder) holder;
        ContentAdapter mAdapter = new ContentAdapter(activity);
        mAdapter.setData(sparseArray.get(position));
        LinearLayoutManager manager= new LinearLayoutManager(activity);
        PagerSnapHelper horSnapHelper = new PagerSnapHelper();
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horSnapHelper.attachToRecyclerView(firstViewHolder.recycler_first);
        firstViewHolder.recycler_first.setLayoutManager(manager);
        firstViewHolder.recycler_first.setAdapter(mAdapter);

//        if (isHor) {
//            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            horSnapHelper.attachToRecyclerView(recycleView);
//        } else {
//            manager.setOrientation(LinearLayoutManager.VERTICAL);
//            horSnapHelper.attachToRecyclerView(null);
//        }
//        recycleView.setLayoutManager(manager);
//
//        recycleView.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return sparseArray.size();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycler_first;

        public FirstViewHolder(View itemView) {
            super(itemView);
            recycler_first = itemView.findViewById(R.id.recycle_first);
        }
    }
}
