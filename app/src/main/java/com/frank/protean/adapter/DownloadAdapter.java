package com.frank.protean.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.frank.protean.bean.FileInfo;

import java.util.List;

/**
 * Created by maowenqiang on 16/8/6.
 */
public class DownloadAdapter extends BaseAdapter{
    private Context context;
    private List<FileInfo> list;

    public DownloadAdapter(Context context,List<FileInfo> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
