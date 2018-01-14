package com.frank.protean;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.frank.protean.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 2017/12/4.
 */

public class ListActivity extends AppCompatActivity {
    private RecyclerView recycle;
    private ListAdapter mAdapter;
    private List<Integer> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initData();
        recycle = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycle.setLayoutManager(manager);
        mAdapter = new ListAdapter(this);
        mAdapter.setData(data);
        recycle.setAdapter(mAdapter);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFirstData();
            }
        });
    }

    private void addFirstData() {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            temp.add(i);
        }
        mAdapter.addFirstData(temp);
    }

    private void initData() {
        for (int i = 5; i < 15; i++) {
            data.add(i);
        }
    }
}
