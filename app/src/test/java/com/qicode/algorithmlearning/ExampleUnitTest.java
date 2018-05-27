package com.qicode.algorithmlearning;

import org.junit.Test;

import List.dlinkedlist.DLinkedList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        DLinkedList<Integer> dLinkedList = new DLinkedList<>();
        dLinkedList.add(1);
        dLinkedList.add(1);
        dLinkedList.add(1);
        dLinkedList.add(1);
        dLinkedList.add(2);
        dLinkedList.add(3);
//        dLinkedList.add(1, 4);
//        dLinkedList.add(6, 5);
//        dLinkedList.remove(0);
        dLinkedList.set(1, 10);
        dLinkedList.removeAll(1);
        dLinkedList.printItems();
//        dLinkedList.clear();
        System.out.println(dLinkedList.size());
        System.out.println(dLinkedList.contains(2));
    }
}