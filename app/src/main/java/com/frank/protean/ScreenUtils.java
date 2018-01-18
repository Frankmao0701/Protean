package com.frank.protean;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by jerry on 2016/11/2.
 * 屏幕相关
 */
public class ScreenUtils {

    private static DisplayMetrics mMetrics;
    private static int mScreenWidth;
    private static int mScreenHeight;

    public static boolean isUnderHDPI(final Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.density <= 1.5;
    }

    public static boolean isUnderXHDPI(final Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.density <= 2.0;

    }

    public static int[] getWindowSize(Context context) {
        if (mMetrics == null) {
            mMetrics = context.getResources().getDisplayMetrics();
        }
        if (mMetrics != null) {
            return new int[]{mMetrics.widthPixels, mMetrics.heightPixels};
        }
        return new int[]{0, 0};
    }

    public static int getScreenWidth(Context context) {
        if(mScreenWidth == 0){
            initScreenSize(context);
        }
        return mScreenWidth;
    }

    public static int getScreenHeight(Context context){
        if(mScreenWidth == 0){
            initScreenSize(context);
        }
        return mScreenHeight;
    }

    private static void initScreenSize(Context context){
        int[]size = getWindowSize(context);
        mScreenWidth = size[0];
        mScreenHeight = size[1];
    }
}
