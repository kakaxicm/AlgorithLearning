package com.qicode.algorithmlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sort.Sort;

public class MainActivity extends AppCompatActivity {
    private int[] mArray = {5,3,4,6,2,7,10,8,12,4,1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Sort.bubbleSort(mArray);
//        Sort.selectSort(mArray);
//        Sort.insertSort(mArray);
//        Sort.puickSort(mArray);
//        Sort.shellSort(mArray);
//        Sort.mergeSort(mArray);
//        Sort.countSort(mArray);
        Sort.bucketSort(mArray);
    }
}
