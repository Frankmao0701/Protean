package com.frank.protean.mode.cloneable;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式mode
 */
public class WordDocument implements Cloneable {
    public WordDocument() {
        Log.e("原型模式::", "构造函数调用");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    private String name;
    private ArrayList<String> images;

    @Override
    public WordDocument clone() {
        WordDocument document = null;
        try {
            document = (WordDocument) super.clone();
            document.name = this.name;
            document.images = (ArrayList<String>) this.images.clone();
            return document;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showDoc() {
        Log.e("原型模式::", "name：：" + name);
        for (String image : images) {
            Log.e("原型模式::", "image" + image);
        }

    }
}
