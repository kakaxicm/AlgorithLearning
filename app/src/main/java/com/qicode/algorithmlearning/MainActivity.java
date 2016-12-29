package com.qicode.algorithmlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import List.ArrayList;
import List.Iterator;
import List.LinkedList;
import List.Person;
import sort.Sort;
import stack.HanoiTower;
import stack.Stack;

public class MainActivity extends AppCompatActivity {
    private int[] mArray = {5, 3, 4, 6, 2, 7, 10, 8, 12, 4, 1};

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
//        list.remove(new Person("2"));
//        list.removeFromHead();
//        list.addToHead(new Person("headperson"));
//        list.removeFromtail();
//        list.removeFromtail();
        for(int i = 0; i < list.size(); i++){
            Log.e("TAG", list.get(i).toString());
        }
//        Iterator<Person> iterator = list.iterator();
//        while(iterator.hasNext()){
//            Log.e("TAG==", iterator.next().toString());
//            iterator.remove();
//        }

//        Log.e("TAG", list.size()+"");

        //Stack测试
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.pop();
//        stack.push(4);
//        Log.e("TAG", stack.size()+"=="+stack.peek());
//        stack.clear();
//        while (!stack.isEmpty()){
//            Log.e("TAG", stack.pop()+"");
//        }
//        Log.e("TAG", stack.size()+"==");
        //hanoitest
        Stack<Integer> x = new Stack<>();
        Stack<Integer> y = new Stack<>();
        Stack<Integer> z = new Stack<>();
        x.ID = "x";
        y.ID = "y";
        z.ID = "z";
        int n = 5;
        for (int i = n; i >= 1; i--) {
            x.push(i);
        }
        new HanoiTower().hanoi(n, x, y, z);
    }
}
