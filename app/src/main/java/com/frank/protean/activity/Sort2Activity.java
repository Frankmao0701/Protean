package com.frank.protean.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.frank.protean.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Frankmao on 2018/4/27.
 */

public class Sort2Activity extends AppCompatActivity {
    private Button btn_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        Log.e("Sort","onCreate...");
        int[] array = new int[]{2, 3, 1, 9, 8, 4, 7, 10, 5};
        buttleSort(array);
//        quickSort(array, 0, array.length - 1);
//        Log.e("Sort", "quick sort:" + Arrays.toString(array));
//        list.add()
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("Sort","onConfigurationChanged"+newConfig.orientation);
    }

    private void quickSort(int[] array, int low, int high) {
        int start = low;
        int end = high;
        int key = array[low];
        while (end > start) {
            while (end > start && array[end] >= key) {
                end--;
            }
            if (array[end] <= key) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }
            while (end > start && array[start] <= key) {
                start++;
            }
            if (array[start] >= key) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
        }
        if (start > low) quickSort(array, low, start - 1);
        if (end < high) quickSort(array, end + 1, high);
    }

    private void buttleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] >= array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }
        }
    }
    public void rm(ArrayList<Object> list,int[] indexs){
        Iterator iterator = (Iterator) list.iterator();
        while (iterator.hasNext()){
            for (int i = 0 ; i<indexs.length;i++){
                if (iterator.next().equals(indexs[i])){
                    iterator.remove();
                }
            }
        }
    }

    public void rm2(ArrayList<Object> list,int[] indexs){
        Iterator iterator = list.iterator();
        Arrays.sort(indexs);
        int i = -1;
        int j = 0;
        while (iterator.hasNext()){
            iterator.next();
            i++;
            if(i == indexs[j]){
                iterator.remove();
                j++;
            }
        }
    }
    public void rm3(ArrayList<Object> list,int[] indexs){
        Iterator iterator = list.iterator();
        Arrays.sort(indexs);
        int i = -1;
        int j = 0;
        while (iterator.hasNext()){
            iterator.next();
            i++;
            if(i == indexs[j]){
                iterator.remove();
                j++;
            }
        }
    }
    public void rm4(ArrayList<Object> list,int[] indexs){
        Iterator iterator = list.iterator();
        Arrays.sort(indexs);
        int i = -1;
        int j = 0;
        while (iterator.hasNext()){
            iterator.next();
            i++;
            if(i == indexs[j]){
                iterator.remove();
                j++;
            }
        }
    }
}
