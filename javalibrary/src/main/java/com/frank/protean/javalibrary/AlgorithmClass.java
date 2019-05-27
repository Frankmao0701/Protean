package com.frank.protean.javalibrary;

public class AlgorithmClass {
    public static void main(String[] args) {
//        maopao();
        xuanzhe();
    }

    /**
     * 冒泡排序总的平均时间复杂度为：O(n2) 。
     */
    private static void maopao() {
        int[] arr = new int[]{5, 1, 2, 4, 3, 7, 6};
        for (int i = 0; i < arr.length - 1; i++) { //外层循环控制排序趟数   length-1
            for (int j = 0; j < arr.length - 1 - i; j++) { //内层循环控制每一趟排序多少次 length - 1 - i
                int temp = 0;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        printArr(arr);
    }

    /**
     * 选择排序平均时间复杂度为:O(n2)。
     */
    private static void xuanzhe() {
        int[] arr = new int[]{3, 1, 8, 6, 2};
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int flag = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < temp) {
                    temp = arr[j];
                    flag = j;
                }
            }
            if (flag != i) {
                arr[flag] = arr[i];
                arr[i] = temp;
            }
        }
        printArr(arr);
    }

    private static void printArr(int arr[]) {
        for (int result : arr) {
            System.out.println(result);
        }
    }

}
