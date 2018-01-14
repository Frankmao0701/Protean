package com.frank.protean.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frank.protean.R;

import java.util.List;

/**
 * Created by maowenqiang on 16/8/11.
 */
public class DetailPopAdapter extends BaseAdapter{

    private Context context;

    private List<String> list;

    public DetailPopAdapter(Context context, List<String> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder holder;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_group_item, null);
            holder=new ViewHolder();

            convertView.setTag(holder);

            holder.groupItem=(TextView) convertView.findViewById(R.id.groupItem);

        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.groupItem.setTextColor(Color.BLACK);
        holder.groupItem.setText(list.get(position));
        holder.groupItem.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher,0,0,0);

        return convertView;
    }

    static class ViewHolder {
        TextView groupItem;
    }
}
