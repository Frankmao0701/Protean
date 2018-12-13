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

public class ALauchActivity extends BaseActivity {
    public static int id = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauch_a);
//        Log.e(TAG, "onCreate:::");
        id = 2;
        Log.e(TAG, "id:::" + id);
        findViewById(R.id.btn_jump_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ALauchActivity.this, BLauchActivity.class);
                ALauchActivity.this.getApplication().startActivity(intent);
            }
        });
    }
}
