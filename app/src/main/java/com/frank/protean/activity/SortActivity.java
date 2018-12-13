package com.frank.protean.viewdispatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.frank.protean.R;

/**
 * Created by Frankmao on 2018/4/27.
 */

public class DispatchActivity extends AppCompatActivity {
    private Button btn_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
//        Log.e("DispatchActivity", ViewConfiguration.get(this).getScaledTouchSlop() + "");
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("dispatch", "btn_click::");
            }
        });
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View view, View view1) {

            }
        });
    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        if (hasFocus) {
//            Log.e("DispatchActivity", "onWindowFocusChanged" + btn_test.getMeasuredWidth() + "::" + btn_test.getMeasuredHeight());
//        }
//    }
}
