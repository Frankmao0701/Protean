package com.frank.protean.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.frank.protean.R;

import java.util.List;

/**
 * Created by Frank on 2017/12/4.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Activity mContext;

    public ListAdapter(Activity mContext) {
        this.mContext = mContext;
        setHasStableIds(true);
    }

    private List<Integer> data;

    public void setData(List<Integer> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addFirstData(List<Integer> temp) {
        data.addAll(0, temp);
        notifyItemRangeInserted(0, temp.size());
        notifyItemRangeChanged(temp.size(), data.size() - temp.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(data.get(position) + "");
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ListAdapter","pos::" + position + "data::" + data.get(position));
//                Toast.makeText(mContext, "pos::" + position + "data::" + data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
