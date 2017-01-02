package com.qicode.algorithmlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import List.ArrayList;
import List.Iterator;
import List.LinkedList;
import List.Person;
import queue.Queue;
import sort.Sort;
import stack.HanoiTower;
import stack.PostFixExpression;
import stack.Stack;
import tree.BinaryNode;
import tree.BinaryTree;

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
//        LinkedList<Person> list = new LinkedList<Person>();
//        Person removePerson = new Person("2");
//        list.add(new Person("1"));
//        list.add(new Person("2"));
//        list.add(new Person("3"));
//        list.add(new Person("4"));
//        list.remove(new Person("2"));
//        list.removeFromHead();
//        list.addToHead(new Person("headperson"));
//        list.removeFromtail();
//        list.removeFromtail();
//        for(int i = 0; i < list.size(); i++){
//            Log.e("TAG", list.get(i).toString());
//        }
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
//        Stack<Integer> x = new Stack<>();
//        Stack<Integer> y = new Stack<>();
//        Stack<Integer> z = new Stack<>();
//        x.ID = "x";
//        y.ID = "y";
//        z.ID = "z";
//        int n = 5;
//        for (int i = n; i >= 1; i--) {
//            x.push(i);
//        }
//        new HanoiTower().hanoi(n, x, y, z);
//        /**
//         *队列测试
//         */
//        Queue<Integer> queue = new Queue<>();
//        queue.enquene(1);
//        queue.enquene(2);
//        queue.enquene(3);
//        queue.enquene(4);
//
//        queue.dequeue();
//
//        while (!queue.isEmpty()){
//            Log.e("TAG", queue.dequeue().toString());
//        }
//        for(int i = 0; i < queue.size(); i++){
//            Log.e("TAG", queue.getItem(i).toString());
//        }

        /**
         * a+b*c+(d*e+f)*g
         */
//        new PostFixExpression().convertMidToPostFixExpression("a+b*c+(d*e+f)*g");
//        new PostFixExpression().convertMidToPostFixExpression("((2+3)*8+5+3)*6");
//        new PostFixExpression().calulateExpression("((2+3)*8+5+9/3)*6");
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(5);
        tree.insert(7);
        tree.insert(3);
        tree.insert(4);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(2);
        tree.insert(8);
//        BinaryNode<Integer> removedNode = tree.remove(1, tree.mRoot);
//        Log.e("TAG", tree.findMax()+","+tree.findMin()+", size="+tree.size());
//        BinaryNode<Integer> node = tree.findNode(3);
//        Log.e("TAG", "node = "+tree.contains(8)+","+node.mDupCount);
//        Log.e("TAG", "pretrans = "+tree.preOrder());
//        Log.e("TAG", "intrans = "+tree.inOrder());

        //前序和中序数组构建二叉树测试
        Integer[] preArr = {5,3,1,2,4,7,6,8};
        Integer[] inArr = {1,2,3,4,5,6,7,8};
        BinaryNode<Integer> root = new BinaryTree<Integer>().createBinaryTreeByPreIn(preArr, inArr, 0, preArr.length-1, 0, inArr.length-1);

        Log.e("TAG", "pretrans = "+tree.preOrder(root));
        Log.e("TAG", "intrans = "+tree.inOrder(root));


    }
}
