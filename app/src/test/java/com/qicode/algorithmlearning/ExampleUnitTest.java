package com.qicode.algorithmlearning;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import List.dlinkedlist.DLinkedList;
import stack.Stack;
import tree.BinaryNode;
import tree.BinaryTree;
import tree.HuffmanModel;
import tree.SearchTree;
import tree.avltree.AVLNode;
import tree.avltree.AVLTree;


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
        System.out.println(tree.size() + "");
        System.out.println("=====高度=====");
        System.out.println(tree.height() + "");
        System.out.println("=====最小值=====");
        System.out.println(tree.findMin() + "");
        System.out.println("=====最大值=====");
        System.out.println(tree.findMax() + "");
        System.out.println("=====是否包含元素=====");
        System.out.println(tree.contains(1) + "");
        System.out.println("=====删除元素=====");
        tree.remove(6);
        dumpTree(nodes, tree);
        System.out.println("=====根据前序和中序数组构建二叉树=====");
        Integer[] preArr = {8, 6, 5, 3, 1, 4, 7, 10, 9, 11};
        Integer[] inArr = {1, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Integer[] postArr = {1, 4, 3, 5, 7, 6, 9, 11, 10, 8};
        SearchTree<Integer> newTree = tree.buildTreeByPreIn(preArr, inArr);
        System.out.println("=====先序遍历=====");
        System.out.println(newTree.preOrder());
        System.out.println("=====中序遍历=====");
        System.out.println(newTree.inOrder());
        System.out.println("=====后序遍历=====");
        System.out.println(newTree.postOrder());
        System.out.println("=====层序遍历=====");
        System.out.println(newTree.levelOrder());

        System.out.println("=====根据后序和中序数组构建二叉树=====");
        SearchTree<Integer> newTree2 = tree.buildTreeByPostIn(postArr, inArr);
        System.out.println("=====先序遍历=====");
        System.out.println(newTree2.preOrder());
        System.out.println("=====中序遍历=====");
        System.out.println(newTree2.inOrder());
        System.out.println("=====后序遍历=====");
        System.out.println(newTree2.postOrder());
        System.out.println("=====层序遍历=====");
        System.out.println(newTree2.levelOrder());

    }

    private void dumpTree(int[] nodes, SearchTree<Integer> tree) {
        for (int i = 0; i < nodes.length; i++) {
            BinaryNode<Integer> node = tree.findNode(nodes[i]);
            if (node != null) {
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

    @Test
    public void testHuffman() {
        System.out.println("=====赫夫曼=====");
        Integer[] huffmanArr = {1, 2, 3, 4, 5};//简单起见,权重=编码数据,升序排列
        BinaryNode<HuffmanModel> huffmanTree = BinaryTree.createHuffmanTree(huffmanArr);
        HashMap<String, Integer> huffmanEncodeTable = new HashMap<>();
        //赫夫曼编码huffmanEncodeTable保存赫夫曼编码
        BinaryTree.huffmanEncode(huffmanTree, huffmanEncodeTable);
        System.out.println("=====赫夫曼编码输出=====");
        Iterator<Map.Entry<String, Integer>> iterator = huffmanEncodeTable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey() + "==" + next.getValue());
        }
        System.out.println("=====赫夫曼解码输出=====");

        iterator = huffmanEncodeTable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey() + "==" + BinaryTree.huffmanDecode(huffmanTree, next.getKey()));
        }
    }

    @Test
    public void testAVLTree() {
        Integer[] avlArr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < avlArr.length; i++) {
            avlTree.insert(avlArr[i]);
        }
        System.out.println("=====平衡二叉树输出=====");
        dumpAvlTree(avlTree.mRoot);//树结构打印测试
        avlTree.remove(6);
        System.out.println("=====平衡二叉树深度=====");
        System.out.println(avlTree.height());
        System.out.println("=====平衡二叉树大小=====");
        System.out.println(avlTree.size());
        System.out.println("=====平衡二叉树删除元素后输出=====");
        dumpAvlTree(avlTree.mRoot);
        System.out.println("=====平衡二叉树深度=====");
        System.out.println(avlTree.height());
    }

    /**
     * 打印AVL树信息
     *
     * @param root
     */
    public void dumpAvlTree(AVLNode<Integer> root) {
        if (root == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.data);
        sb.append("(");
        if (root.left != null && root.right != null) {
            sb.append(root.left.data + "-" + root.left.height);
            sb.append(" , ");
            sb.append(root.right.data + "-" + root.right.height);
        } else if (root.left != null) {
            sb.append(root.left.data + "-" + root.left.height);
            sb.append(" , ");
        } else if (root.right != null) {
            sb.append(" , ");
            sb.append(root.right.data + "-" + root.right.height);
        }
        sb.append(")");
        System.out.println(sb.toString());


        dumpAvlTree(root.left);
        dumpAvlTree(root.right);

    }

    /**
     * 测试哈希碰撞
     */
    @Test
    public void testHashHit() {
        HashA a = new HashA("ABC");
        HashB b = new HashB("ABC");
        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put(a, "1");
        hashMap.put(b, "2");
        System.out.println("=====哈希冲突测试====");
        System.out.println(hashMap.get(a));
        System.out.println(hashMap.get(b));
    }
}