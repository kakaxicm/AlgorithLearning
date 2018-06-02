package com.qicode.algorithmlearning;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import List.dlinkedlist.DLinkedList;
import stack.Stack;
import tree.BinaryNode;
import tree.SearchTree;


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

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            iterator.remove();
        }
    }

    @Test
    public void testStack() {
//        Stack测试
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.push(4);
        System.out.println(stack.size() + "==" + stack.peek());
//        stack.clear();
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + "");
        }
        System.out.println(stack.size() + "==");
    }

    @Test
    public void testSearchTree() {
        int[] nodes = {8, 10, 6, 5, 7, 9, 11, 3, 1, 4};
        SearchTree<Integer> tree = new SearchTree<>();
        for (int i = 0; i < nodes.length; i++) {
            tree.insert(nodes[i]);
        }
        dumpTree(nodes, tree);
        System.out.println("=====先序遍历=====");
        System.out.println(tree.preOrder());
        System.out.println("=====中序遍历=====");
        System.out.println(tree.inOrder());
        System.out.println("=====后序遍历=====");
        System.out.println(tree.postOrder());
        System.out.println("=====层序遍历=====");
        System.out.println(tree.levelOrder());
        System.out.println("=====大小=====");
        System.out.println(tree.size()+"");
        System.out.println("=====高度=====");
        System.out.println(tree.height()+"");
        System.out.println("=====最小值=====");
        System.out.println(tree.findMin()+"");
        System.out.println("=====最大值=====");
        System.out.println(tree.findMax()+"");
        System.out.println("=====是否包含元素=====");
        System.out.println(tree.contains(1)+"");
        System.out.println("=====删除元素=====");
        tree.remove(8);
        dumpTree(nodes,tree);

    }

    private void dumpTree(int[] nodes, SearchTree<Integer> tree) {
        for (int i = 0; i < nodes.length; i++) {
            BinaryNode<Integer> node = tree.findNode(nodes[i]);
            if(node != null){
                System.out.println(node.data);
                if (node.left != null) {
                    System.out.println("    --" + node.left.data);
                }
                if (node.right != null) {
                    System.out.println("    --" + node.right.data);
                }
            }

        }
    }
}