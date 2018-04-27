package com.frank.protean.wiget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Frank on 2018/1/10.
 */

public class MyLinerlayout extends LinearLayout {
    public MyLinerlayout(Context context) {
        super(context);
    }

    public MyLinerlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinerlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float downX;
    private float downY;
    private long downTime;


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downTime = System.currentTimeMillis();
            downX = ev.getX();
            downY = ev.getY();
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            float upX = ev.getX();
            float upY = ev.getY();
            if (System.currentTimeMillis() - downTime < 500 && (Math.abs(upX - downX) < 20 && Math.abs(upY - downY) < 20)) {
                if (upX > 0 && upX <= this.getWidth() && upY >= this.getHeight() / 3 && upY < this.getHeight() / 3 * 2) {
                    Log.e("ViewTransmit", "middleClick");
                }
                return true;
            }
        }
        return super.onTouchEvent(ev);
    }
}
