package com.frank.protean.mode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;

import com.frank.protean.R;
import com.frank.protean.mode.cloneable.WordDocument;

import java.util.ArrayList;
import java.util.List;

public class ModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mode);
//        singleMode();
//        builderMode();
        concreatePrototype();
    }

    private void singleMode() {
        LayoutInflater.from(this);//ServiceFetcher 缓存保证对象唯一性
    }

    /**
     * Builder模式
     */
    private void builderMode() {
//        Builder builder = new MacbookBuilder();
//        Director director = new Director(builder);
//        director.construct("英特尔i7", "Retina显示器");

        Log.e("ModeActivity::", new MacbookBuilder().buildBoard("英特尔i7").buildDisplay("Retina显示器").buildOS().create().toString());
    }

    /**
     * 浅拷贝和深拷贝
     * 原型模式
     */
    private void concreatePrototype(){

        //浅拷贝
        WordDocument wordDocument = new WordDocument();
        wordDocument.setName("Frank");
        ArrayList<String> list = new ArrayList<>();
        list.add("图片1");
        list.add("图片2");
        wordDocument.setImages(list);

        Log.e("原型模式::","copy后的对象...");
        WordDocument copyDoc = wordDocument.clone();
        copyDoc.setName("Fiona");
        copyDoc.getImages().add("图片3");
        copyDoc.showDoc();

        Log.e("原型模式::","原来的对象...");
        wordDocument.showDoc();
    }
}
