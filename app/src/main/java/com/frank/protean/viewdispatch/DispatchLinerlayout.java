package com.frank.protean.viewdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
