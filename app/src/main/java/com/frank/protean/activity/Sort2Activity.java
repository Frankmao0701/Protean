package com.frank.protean.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.frank.protean.R;

import java.util.Arrays;

/**
 * Created by Frankmao on 2018/4/27.
 */

public class SortActivity extends AppCompatActivity {
    private Button btn_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        int[] array = new int[]{2, 3, 1, 9, 8, 4};
//        buttleSort(array);
        quickSort1(array, 0, array.length - 1);
        Log.e("Sort", "quick sort:" + Arrays.toString(array));
    }

    public void buttleSort(int[] arr) {
        int t = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        Log.e("Sort", "after::" + Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int low, int high) {
        int pos, t, pivot, i;
        if (low < high) {
            pos = low;
            pivot = arr[pos];
            for (i = low + 1; i <= high; i++) {
                if (arr[i] < pivot) {
                    pos++;
                    t = arr[pos];
                    arr[pos] = arr[i];
                    arr[i] = t;
                }
            }
            t = arr[low];
            arr[low] = arr[pos];
            arr[pos] = t;
            quickSort(arr, low, pos - 1);
            quickSort(arr, pos + 1, high);
        }
        Log.e("Sort", "quick sort:" + Arrays.toString(arr));
    }

    public void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];


        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) sort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
    }

    public void quickSort1(int[] arr, int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];
        while (start < end) {
            while (start < end && arr[end] >= key) {
                end--;
            }
            if (arr[end] <= key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }
            while (start < end && arr[start] <= key) {
                start++;
            }
            if (arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }

        }
        if (start > low) quickSort1(arr, low, start - 1);
        if (end < high) quickSort1(arr, end + 1, high);
    }

//    public void quickSort(int array[], int low, int high) {// 传入low=0，high=array.length-1;
//        int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
//        if (low < high) {
//            p_pos = low;
//            pivot = array[p_pos];
//            for (i = low + 1; i <= high; i++)
//                if (array[i] > pivot) {
//                    p_pos++;
//                    t = array[p_pos];
//                    array[p_pos] = array[i];
//                    array[i] = t;
//                }
//            t = array[low];
//            array[low] = array[p_pos];
//            array[p_pos] = t;
//            // 分而治之
//            quickSort(array, low, p_pos - 1);// 排序左半部分
//            quickSort(array, p_pos + 1, high);// 排序右半部分
//                    Log.e("Sort","quick sort:"+Arrays.toString(array));
//
//        }
//
//    }
}
