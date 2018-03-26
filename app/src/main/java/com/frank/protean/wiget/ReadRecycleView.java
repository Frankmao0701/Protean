package com.frank.protean.wiget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by Frankmao on 2017/7/21.
 */

public class ReadRecycleView extends RecyclerView {
    private Context mContext;
    private OnReadViewClickListener listener;
    private boolean isRight;
    private boolean isHor;

    public ReadRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public ReadRecycleView(Context context) {
        super(context);
        this.mContext = context;
    }

    /**
     * 设置翻页模式
     *
     * @param isRight 是否为右手模式
     * @param isHor   是否是横向翻页
     */
    public void setMode(boolean isRight, boolean isHor) {
        this.isRight = isRight;
        this.isHor = isHor;
    }

    public void setOnReadViewClickListener(OnReadViewClickListener listener) {
        this.listener = listener;
    }

    private long downTime;
    private float downX;
    private float downY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downTime = System.currentTimeMillis();
            downX = ev.getX();
            downY = ev.getY();
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            float upX = ev.getX();
            float upY = ev.getY();
            if (System.currentTimeMillis() - downTime < 500 && (Math.abs(upX - downX) < 20 && Math.abs(upY - downY) < 20)) {
                if (isHor) { //横向翻页判断左右手模式,区域不同
                    if (isRight) {
                        if (upX > 0 && upX < this.getWidth() / 3 && upY > 0 && upY < this.getHeight()) {
                            if (listener != null) {
                                listener.onLastPageClick(this);
                            }
                            return super.dispatchTouchEvent(ev);
                        } else if (upX >= this.getWidth() / 3 && upX < this.getWidth() * 2 / 3 && upY > this.getHeight() / 11 * 3 && upY < this.getHeight() / 11 * 8) {
                            if (listener != null) {
                                listener.onMiddleClick();
                            }
                            return super.dispatchTouchEvent(ev);
                        } else {
                            if (listener != null) {
                                listener.onNextPageClick(this);
                            }
                            return super.dispatchTouchEvent(ev);
                        }
                    } else {
                        if (upX > this.getWidth() * 2 / 3 && upX < this.getWidth() && upY > 0 && upY < this.getHeight()) {
                            if (listener != null) {
                                listener.onNextPageClick(this);
                            }
                            return super.dispatchTouchEvent(ev);
                        } else if (upX >= this.getWidth() / 3 && upX < this.getWidth() * 2 / 3 && upY > this.getHeight() / 11 * 3 && upY < this.getHeight() / 11 * 8) {
                            if (listener != null) {
                                listener.onMiddleClick();
                            }
                            return super.dispatchTouchEvent(ev);
                        } else {
                            if (listener != null) {
                                listener.onLastPageClick(this);
                            }
                            return super.dispatchTouchEvent(ev);
                        }
                    }
                } else { //竖向翻页不考虑左右手 上中下三个区域
//                    if (upX > 0 && upX <= this.getWidth() && upY >= this.getHeight() / 3 && upY < this.getHeight() / 3 * 2) {
//                        if (listener != null) {
//                            listener.onMiddleClick();
//                        }
//                        return super.dispatchTouchEvent(ev);
//                    }
                    if (upX > 0 && upX <= this.getWidth() && upY > 0 && upY < this.getHeight() / 3) {
                        if (listener != null) {
                            listener.onLastPageClick(this);
                        }
                        return super.dispatchTouchEvent(ev);
                    } else if (upX > 0 && upX <= this.getWidth() && upY >= this.getHeight() / 3 && upY < this.getHeight() / 3 * 2) {
                        if (listener != null) {
                            listener.onMiddleClick();
                        }
                        return super.dispatchTouchEvent(ev);
                    } else {
                        if (listener != null) {
                            listener.onNextPageClick(this);
                        }
                        return super.dispatchTouchEvent(ev);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

}
