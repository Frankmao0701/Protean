package com.frank.protean.viewdispatch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Frankmao on 2018/4/27.
 */

public class DispatchLinerlayout extends LinearLayout {

    public DispatchLinerlayout(Context context) {
        super(context);
    }

    public DispatchLinerlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchLinerlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean consume = false;
//        if (onInterceptTouchEvent(ev)) {
//            consume = onTouchEvent(ev);
//        } else {
//            consume = child.dispatchTouchEvent(ev);
//        }
//        return consume;
//    }
private long downTime;
    private float downX;
    private float downY;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downTime = System.currentTimeMillis();
            downX = event.getX();
            downY = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float upX = event.getX();
            float upY = event.getY();
            if (upX > 0 && upX < this.getWidth() / 3 && upY > 0 && upY < this.getHeight()) {
                Log.e("dispatch:","onLeft");
                return true;
            } else if (upX >= this.getWidth() / 3 && upX < this.getWidth() * 2 / 3 && upY > this.getHeight() / 11 * 3 && upY < this.getHeight() / 11 * 8) {
                Log.e("dispatch:","onMiddle");
                return true;

            } else {
                Log.e("dispatch:","onRight");
                return true;

            }
        }
        return super.onTouchEvent(event);
    }
}
