package com.qicode.algorithmlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import List.ArrayList;
import List.Iterator;
import List.LinkedList;
import List.Person;
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
        LinkedList<Person> list = new LinkedList<Person>();
        Person removePerson = new Person("2");
        list.add(new Person("1"));
        list.add(new Person("2"));
        list.add(new Person("3"));
        list.add(new Person("4"));
        list.remove(new Person("2"));
//        for(int i = 0; i < list.size(); i++){
//            Log.e("TAG", list.get(i).toString());
//        }
        Iterator<Person> iterator = list.iterator();
        while(iterator.hasNext()){
            Log.e("TAG==", iterator.next().toString());
            iterator.remove();
        }

        Log.e("TAG", list.size()+"");

    }
}
