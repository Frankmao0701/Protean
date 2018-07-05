package com.frank.protean.animation;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.frank.protean.R;

/**
 * Created by Frankmao on 2018/7/2.
 */

public class PropertyActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv;
    private Button btn_trans;
    private ImageView levelImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        tv = (TextView) findViewById(R.id.tv);
        btn_trans = (Button) findViewById(R.id.btn_trans);
        levelImageView = (ImageView) findViewById(R.id.img_level);
        btn_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewWrapper viewWrapper = new ViewWrapper(btn_trans);
//                ObjectAnimator.ofInt(viewWrapper, "width", 600).setDuration(3000).start();
//                performAnimation(btn_trans, btn_trans.getWidth(), 600);
                performImage();
            }
        });
        tv.setOnClickListener(this);
        ValueAnimator colorAnim = ObjectAnimator.ofInt(tv, "backgroundColor",/*Red*/0xFFF8080,/*Blue*/0xFF8080FF);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

    private void performImage() {
        levelImageView.setImageResource(R.drawable.drawable_refresh);
        levelImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(levelImageView, "imageLevel", 1, 4);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }

    @Override
    public void onClick(View v) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(tv, "rotationX", 0, 360),
                ObjectAnimator.ofFloat(tv, "rotationY", 0, 180),
                ObjectAnimator.ofFloat(tv, "rotation", 0, -90),
                ObjectAnimator.ofFloat(tv, "translationX", 0, 90),
                ObjectAnimator.ofFloat(tv, "translationY", 0, 90),
                ObjectAnimator.ofFloat(tv, "scaleX", 0, 1.5f),
                ObjectAnimator.ofFloat(tv, "scaleX", 0, 0.5f),
                ObjectAnimator.ofFloat(tv, "alpha", 1, 0.25f, 1)
        );
        set.setDuration(5000).start();
    }

    public class ViewWrapper {
        private Button button;

        public ViewWrapper(Button button) {
            this.button = button;
        }

        public void setWidth(int width) {
            button.getLayoutParams().width = width;
            button.requestLayout();
        }

        public int getWidth() {
            return button.getLayoutParams().width;
        }
    }

    private void performAnimation(final View target, final int from, final int end) {
        ValueAnimator animator = ValueAnimator.ofInt(from, end);
        final IntEvaluator evaluator = new IntEvaluator();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("animation", animation.getAnimatedValue() + ":" + animation.getAnimatedFraction());
                float fraction = animation.getAnimatedFraction();
//                target.getLayoutParams().width = (int) animation.getAnimatedValue();
                target.getLayoutParams().width = evaluator.evaluate(fraction, from, end);
                target.requestLayout();
            }
        });
        animator.setDuration(3000).start();
    }
}
