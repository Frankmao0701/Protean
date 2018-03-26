package com.frank.protean.wiget;

import android.view.View;

/**
 * Created by Frankmao on 2017/7/26.
 * 处理漫画阅读页面不同区域点击事件
 */

public interface OnReadViewClickListener {
    public void onMiddleClick();

    public void onLastPageClick(View view);

    public void onNextPageClick(View view);
}
