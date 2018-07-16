package com.frank.protean.window;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.frank.protean.R;

/**
 * Created by Frankmao on 2018/7/5.
 */

public class WindowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        initFloatButton();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initFloatButton() {
        final WindowManager manager = getWindowManager();
        final Button button = new Button(this);
        button.setText("button");
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 100;
        params.y = 300;
        manager.addView(button, params);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        params.x = (int) rawX;
                        params.y = (int) rawY;
                        manager.updateViewLayout(button, params);
                        break;
                }
                return false;
            }
        });
    }
}
