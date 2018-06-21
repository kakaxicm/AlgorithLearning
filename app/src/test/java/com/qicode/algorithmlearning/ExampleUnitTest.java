package com.qicode.algorithmlearning;

import android.util.Log;

import com.qicode.algorithmlearning.dp.LCS;
import com.qicode.algorithmlearning.dp.StepCombination;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import List.dlinkedlist.DLinkedList;
import graphic.Dijkstra;
import graphic.MatrixGraph;
import graphic.Prim;
import graphic.TopologySort;
import sort.Sort;
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
     * 测试哈希基本操作
     */
    @Test
    public void testHashMap() {
        HashA a = new HashA("ABC");
        HashB b = new HashB("ABC");
        //HashMap的key可以为空
        hash.HashMap<Object, String> hashMap = new hash.HashMap<>();
        hashMap.put(null, "90");
        System.out.println(hashMap.get(null));

        hash.HashMap<Object, String> myHashMap = new hash.HashMap<>();
        hash.HashMap<Object, String> myHashCrashMp = new hash.HashMap<>();
        System.out.println("=====哈希冲突测试====");
        myHashCrashMp.put(a, "a");
        myHashCrashMp.put(b, "b");
        System.out.println(myHashCrashMp.get(a));
        System.out.println(myHashCrashMp.get(b));
        List<HashA> keys = new LinkedList<>();
        for (int i = 0; i < 13; i++) {
            HashA hashObj = new HashA("ABC" + i);
            keys.add(hashObj);
            myHashMap.put(hashObj, "" + i);
        }
        System.out.println("=====哈希删除测试====");
        int delIndex = 4;
        myHashMap.remove(keys.get(delIndex));
        keys.remove(delIndex);
        System.out.println("=====哈希大小====");
        System.out.println(myHashMap.size());
        System.out.println("=====哈希打印====");
        for (int i = 0; i < keys.size(); i++) {
            HashA hashObj = keys.get(i);
            String v = myHashMap.get(hashObj);
            System.out.println(v);
        }

    }

    @Test
    public void testSort() {
        int[] arr = {1, 3, 2, 12, 11, 10, 9, 8, 7, 6, 5, 4, 111, 123, 233, 212, 113, 156};
//        Sort.heapSort(arr);
//        Sort.puickSort(arr);
//        Sort.binaryInsertSort(arr);
//        Sort.bubbleSort(arr);
//        Sort.bubbleSort3(arr);
//        Sort.selectSort(arr);
        Sort.radixSort(arr);
//        printArray(arr);
    }

    /**
     * 打印
     *
     * @param arr
     */
    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int item : arr) {
            sb.append(item);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testGraph() {
        /**
         * 图的邻接矩阵描述
         */
        int[] vertexes = new int[]{
                0, 1, 2, 3, 4

        };

        int[][] edges = new int[][]{
                new int[]{0, MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE, 6},
                new int[]{9, 0, 3, MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE},
                new int[]{2, MatrixGraph.NO_WEIGHT_VALUE, 0, 5, MatrixGraph.NO_WEIGHT_VALUE},
                new int[]{MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE, 0, 1},
                new int[]{MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE, MatrixGraph.NO_WEIGHT_VALUE, 0},
        };
        MatrixGraph graph = new MatrixGraph(vertexes, edges);
        System.out.println("邻接矩阵深度优先遍历:");
        graph.transverseDfs();
        System.out.println("邻接矩阵广度优先遍历:");
        graph.transverseBfs();
    }

    /**
     * 创建路径图
     */
    public Dijkstra createGraph() {
        int[] a1 = new int[]{0, 1, 5, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF};
        int[] a2 = new int[]{1, 0, 3, 7, 5, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF};
        int[] a3 = new int[]{5, 3, 0, Dijkstra.INF, 1, 7, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF};
        int[] a4 = new int[]{Dijkstra.INF, 7, Dijkstra.INF, 0, 2, Dijkstra.INF, 3, Dijkstra.INF, Dijkstra.INF};
        int[] a5 = new int[]{Dijkstra.INF, 5, 1, 2, 0, 3, 6, 9, Dijkstra.INF};
        int[] a6 = new int[]{Dijkstra.INF, Dijkstra.INF, 7, Dijkstra.INF, 3, 0, Dijkstra.INF, 5, Dijkstra.INF};
        int[] a7 = new int[]{Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, 3, 6, Dijkstra.INF, 0, 2, 7};
        int[] a8 = new int[]{Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, 9, 5, 2, 0, 4};
        int[] a9 = new int[]{Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, Dijkstra.INF, 7, 4, 0};
        int[][] matrix = new int[9][9];
        matrix[0] = a1;
        matrix[1] = a2;
        matrix[2] = a3;
        matrix[3] = a4;
        matrix[4] = a5;
        matrix[5] = a6;
        matrix[6] = a7;
        matrix[7] = a8;
        matrix[8] = a9;

        Dijkstra map = new Dijkstra(matrix);
        return map;
    }

    @Test
    public void testDijkstra() {
        Dijkstra map = createGraph();
        map.dijkstra(0);
        for (int i = 0; i < 9; i++) {
            map.dumpShortestPatnToDist(i);
        }

    }

    @Test
    public void testTopol() {
        int INF = Integer.MAX_VALUE;
        int[][] map = {
                {0, INF, INF, INF, 1, 1, INF, INF, INF, INF, INF, 1, INF, INF},//0
                {INF, 0, 1, INF, 1, INF, INF, INF, 1, INF, INF, INF, INF, INF},//1
                {INF, INF, 0, INF, INF, 1, 1, INF, INF, 1, INF, INF, INF, INF},//2
                {INF, INF, 1, 0, INF, INF, INF, INF, INF, INF, INF, INF, INF, 1},//3
                {INF, INF, INF, INF, 0, INF, INF, 1, INF, INF, INF, INF, INF, INF},//4
                {INF, INF, INF, INF, INF, 0, INF, INF, 1, INF, INF, INF, 1, INF},//5
                {INF, INF, INF, INF, INF, 1, 0, INF, INF, INF, INF, INF, INF, INF},//6
                {INF, INF, INF, INF, INF, INF, INF, 0, INF, INF, INF, INF, INF, INF},//7
                {INF, INF, INF, INF, INF, INF, INF, 1, 0, INF, INF, INF, INF, INF},//8
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, 0, 1, 1, INF, INF},//9
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 0, INF, INF, 1},//10
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 0, INF, INF},//11
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, 1, INF, INF, 0, INF},//12
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, 0},//13
        };
        TopologySort ts = new TopologySort(map);
        ts.topologySort();
    }

    @Test
    public void testPrim() {
        int INF = Integer.MAX_VALUE;
        int[][] map = {
                {INF, 23, INF, INF, INF, 28, 36},
                {23, INF, 20, INF, INF, INF, 1},
                {INF, 20, INF, 15, INF, INF, 4},
                {INF, INF, 15, INF, 3, INF, 9},
                {INF, INF, INF, 3, INF, 17, 16},
                {28, INF, INF, INF, 17, INF, 25},
                {36, 1, 4, 9, 16, 25, INF}
        };

        Prim prim = new Prim(map);
        prim.prim(0);
    }

    /**
     * 测试最长公共子序列
     */
    @Test
    public void testLcs(){
        LCS lcs = new LCS("BDCABA", "ABCBDAB");
//        lcs.getLcs();
        lcs.getLcss();
    }

    @Test
    public void testStepCombination(){
        StepCombination.stepCombination(5);
    }
}