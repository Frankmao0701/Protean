package com.frank.protean.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.frank.protean.R;

public class FilterActivity extends AppCompatActivity {
    public static final String TAG = "FilterActivity";
    private boolean isShow;
    private NestedScrollView scroll;
    private TextView tv_filter;
    private CoordinatorLayout coordinator;
    private AppBarLayout app_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        tv_filter = findViewById(R.id.tv_filter);
        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName cn = new ComponentName("com.example.myapplication", "com.example.myapplication.MainActivity");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cn);
                startActivity(intent);
            }
        });
//        WindowManager wm = (WindowManager) this
//                .getSystemService(Context.WINDOW_SERVICE);
//        int width = wm.getDefaultDisplay().getWidth();
//        final int height = wm.getDefaultDisplay().getHeight();
//        Log.e(TAG, "宽高=" + width + "::" + height);
//
//        final FilterPopWindow popWindow = new FilterPopWindow(this, 800, 700);
//        tv_filter = findViewById(R.id.tv_filter);
//        coordinator = findViewById(R.id.coordinator);
//        app_bar = findViewById(R.id.app_bar);
//        scroll = findViewById(R.id.scroll);
//        tv_filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int[] location = new int[2];
//                tv_filter.getLocationOnScreen(location);
//                int x = location[0];
//                int y = location[1];
//                Log.e(TAG, "XY=" + x + "::" + y);
//
//                if (isShow) {
//                    isShow = false;
//                    popWindow.showAsDropDown(tv_filter, 0, 0);
//                } else {
//                    isShow = true;
//                    popWindow.showAsDropDown(tv_filter, 0, 0);
//                }
//            }
//        });
    }
}
