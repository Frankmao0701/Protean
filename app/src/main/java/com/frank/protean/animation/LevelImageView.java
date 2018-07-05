package com.frank.protean.animation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Frankmao on 2018/7/4.
 */

public class LevelImageView extends ImageView {
    public LevelImageView(Context context) {
        super(context);
    }

    public LevelImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getImageLevel() {
        return imageLevel;
    }

    @Override
    public void setImageLevel(int imageLevel) {
        super.setImageLevel(imageLevel);
        this.imageLevel = imageLevel;
    }

    private int imageLevel;



}
