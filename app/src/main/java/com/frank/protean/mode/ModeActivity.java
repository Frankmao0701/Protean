package com.frank.protean.mode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;

import com.frank.protean.R;

public class ModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mode);
//        singleMode();
        builderMode();
    }

    private void singleMode() {
        LayoutInflater.from(this);//ServiceFetcher 缓存保证对象唯一性
    }

    /**
     * Builder模式
     */
    private void builderMode() {
//        Builder builder = new MacbookBuilder();
//        Director director = new Director(builder);
//        director.construct("英特尔i7", "Retina显示器");

        Log.e("ModeActivity::", new MacbookBuilder().buildBoard("英特尔i7").buildDisplay("Retina显示器").buildOS().create().toString());
    }
}
