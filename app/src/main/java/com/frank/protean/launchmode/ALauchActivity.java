package com.frank.protean.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.frank.protean.R;
import com.frank.protean.activity.BaseActivity;

/**
 * Created by Frankmao on 2018/4/23.
 */

public class ALauchActivity extends BaseActivity {
    public static int id = 1;
    private LottieAnimationView img_src;
    int index = 0;
    private static final int TIME = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauch_a);
        img_src = findViewById(R.id.img_src);
//        img_src.setAnimation(R.raw.input_listeningloop);
//        img_src.playAnimation();
//        Log.e(TAG, "onCreate:::");
        id = 2;
        Log.e(TAG, "id:::" + id);
        final Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index) {
                    case 0:
                        img_src.setAnimation(R.raw.input_voice_in);
                        img_src.playAnimation();
                        index++;
                        mHandler.postDelayed(this,TIME);
                        break;

                    case 1:
                        img_src.setAnimation(R.raw.input_listeningloop);
                        img_src.playAnimation();
                        index++;
                        mHandler.postDelayed(this,TIME);
                        break;
                    case 2:
                        img_src.setAnimation(R.raw.thinking_in_loop);
                        img_src.playAnimation();
                        index++;
                        mHandler.postDelayed(this,TIME);
                        break;
                    case 3:
                        img_src.setAnimation(R.raw.thinkingresponding_in);
                        img_src.playAnimation();
                        index++;
                        mHandler.postDelayed(this,200);
                        break;
                    case 4:
                        img_src.setAnimation(R.raw.thinkingresponding_voice);
                        img_src.playAnimation();
                        index = 0;
                        break;



                }

            }
        }, 2000);
        findViewById(R.id.btn_jump_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ALauchActivity.this, BLauchActivity.class);
                ALauchActivity.this.getApplication().startActivity(intent);
            }
        });
    }
}
