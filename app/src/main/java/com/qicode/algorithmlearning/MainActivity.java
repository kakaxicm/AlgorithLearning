package com.qicode.algorithmlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import List.ArrayList;
import List.Iterator;
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
//        Sort.bucketSort(mArray);
        //List测试
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(10);
        list.add(12);
        list.add(14);
        list.remove(new Integer(12));
        list.add(1, 8);
//        for(int i = 0; i < list.size(); i++){
//            Log.e("TAG", list.get(i).toString());
//        }
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Log.e("TAG==", iterator.next().toString());
            iterator.remove();
        }

        Log.e("TAG", list.size()+"");

    }
}
