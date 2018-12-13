package com.frank.protean.datastruct;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frank.protean.R;
import com.frank.protean.activity.BaseActivity;

public class DataActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        String str = "链表";
        Node node = new Node(str);
        SingleLinkedList list = new SingleLinkedList<>();
        list.add(0,node);

    }
}
