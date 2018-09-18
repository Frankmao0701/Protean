package com.frank.protean.mode.builder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;

import com.frank.protean.R;

public class ModeActivity extends AppCompatActivity {
    private static final String TAG = ModeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mode);
//        singleMode();
        builderMode();
    }

    private void builderMode() {
        Computer macbook =  new MacbookBuilder().buildBoard("i7cpu").buildDisplay("Retina").buildOs().build();
        Log.i(TAG, macbook.toString());
    }

    private void singleMode() {
        LayoutInflater.from(this);//ServiceFetcher 缓存保证对象唯一性
    }

}
