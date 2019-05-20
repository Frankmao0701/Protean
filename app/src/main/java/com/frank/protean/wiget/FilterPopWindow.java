package com.frank.protean.wiget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.frank.protean.R;

public class FilterPopWindow {
    protected Context context;
    protected View contentView;
    protected PopupWindow mInstance;

    public FilterPopWindow(Context c, int w, int h) {
        context = c;
        initView();
        mInstance = new PopupWindow(contentView, w, h, true);
        initWindow();
    }

    public View getContentView() {
        return contentView;
    }

    public PopupWindow getPopupWindow() {
        return mInstance;
    }

    private void initView(){
         contentView = LayoutInflater.from(context).inflate(
                R.layout.filter_pop_window, null);
    }

    protected void initWindow() {
        mInstance.setOutsideTouchable(true);
        mInstance.setTouchable(true);
    }



    public void showAsDropDown(View anchor, int xoff, int yoff) {
        mInstance.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        mInstance.showAtLocation(parent, gravity, x, y);
    }
}
