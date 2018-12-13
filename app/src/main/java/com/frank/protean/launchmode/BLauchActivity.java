package com.frank.protean.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.frank.protean.R;
import com.frank.protean.activity.BaseActivity;

/**
 * Created by Frankmao on 2018/4/23.
 */

public class BLauchActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauch_b);
        Log.e(TAG, "B id:::"+ALauchActivity.id);
        findViewById(R.id.btn_jumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BLauchActivity.this, CLauchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent:::");
    }
}

