package com.frank.protean.activity;

/**
 * Created by Frankmao on 2018/1/19.
 */


import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.frank.protean.R;

import java.io.File;

public class ScaleImageActivity extends AppCompatActivity {
    SubsamplingScaleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);

        imageView = (SubsamplingScaleImageView) findViewById(R.id.imageView);
//        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
//        imageView.setMinScale(1.0F);//最小显示比例
//        imageView.setMaxScale(10.0F);//最大显示比例（太大了图片显示会失真，因为一般微博长图的宽度不会太宽）
        final String testUrl = "https:\\\\/\\\\/jpapps.qoo-app.com\\\\/comic-test\\\\/1001\\\\/3\\\\/88ee5b866a698e38c3fa294fc1835185.jpg";
        //下载图片保存到本地
//        Glide.with(this)
//                .load(testUrl).downloadOnly(new SimpleTarget<File>() {
//            @Override
//            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
//                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
//                imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
//            }
//        });
    }
}