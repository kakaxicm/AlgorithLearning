package com.qicode.algorithmlearning;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

import List.ArrayList;
import List.Iterator;
import map.Edge;
import map.EdgeListGraph;
import map.MatrixGraph;
import map.Vertex;
import map.VertexForEdge;
import tree.BinaryNode;
import tree.BinaryTree;
import tree.HuffmanModel;

public class MainActivity extends Activity {
    private int[] mArray = {5, 3, 4, 6, 2, 7, 10, 8, 12, 4, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.remove(2);
        arrayList.add(1, 10);
        //迭代器测试
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Log.e("TAG", iterator.next().toString());
            iterator.remove();
        }
        Log.e("TAG", arrayList.size() + "");
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
//        BinaryTree<Integer> tree = new BinaryTree<>();
//        tree.insert(5);
//        tree.insert(7);
//        tree.insert(3);
//        tree.insert(4);
//        tree.insert(3);
//        tree.insert(1);
//        tree.insert(6);
//        tree.insert(2);
//        tree.insert(8);
//        BinaryNode<Integer> removedNode = tree.remove(1, tree.mRoot);
//        Log.e("TAG", tree.findMax()+","+tree.findMin()+", size="+tree.size());
//        BinaryNode<Integer> node = tree.findNode(3);
//        Log.e("TAG", "node = "+tree.contains(8)+","+node.mDupCount);
//        Log.e("TAG", "pretrans = "+tree.preOrder());
//        Log.e("TAG", "intrans = "+tree.inOrder());

        //前序和中序数组构建二叉树测试
//        Integer[] preArr = {5, 3, 1, 2, 4, 7, 6, 8};
//        Integer[] inArr = {1, 2, 3, 4, 5, 6, 7, 8};
//        BinaryNode<Integer> root = new BinaryTree<Integer>().createBinaryTreeByPreIn(preArr, inArr, 0, preArr.length - 1, 0, inArr.length - 1);
//
//
//        /**
//         * 赫夫曼树的创建和编解码
//         */
//        Integer[] huffmanArr = {1, 2, 3, 4, 5};//简单起见,权重=编码数据,升序排列
//        BinaryNode<HuffmanModel> huffmanTree = BinaryTree.createHuffmanTree(huffmanArr);
//        HashMap<String, Integer> huffmanEncodeTable = new HashMap<>();
//        //赫夫曼编码huffmanEncodeTable保存赫夫曼编码
//        BinaryTree.huffmanEnCodeNode(huffmanTree, huffmanEncodeTable);
        /**
         * 编码结果:
         * 0    -- 5
         * 10   -- 4
         * 110  -- 3
         * 1111 -- 2
         * 1110 -- 1
         */
        //TODO 赫夫曼树解码


//        Log.e("TAG", "pretrans = "+tree.preOrder(root));
//        Log.e("TAG", "intrans = "+tree.inOrder(root));

//        int[] arr = {1,2,1,3,3,1,4,1,2,1,3,1,1};
//        int result = Leetcode.getMajorityItem(arr);
//        Log.e("TAG", result+"");

//        int[] arr1 = {1,3,5,7,9,11,13};
//        int[] indexes = Leetcode.getSumIndexes(arr1, 14);
//        for(int i: indexes){
//            Log.e("TAG", i+"");
//        }

//        ListNode l10 = new ListNode(2);
//        ListNode l11 = new ListNode(4);
//        ListNode l12 = new ListNode(5);
//
//        l10.next = l11;
//        l11.next = l12;
//
//        ListNode l20 = new ListNode(5);
//        ListNode l21 = new ListNode(6);
//        ListNode l22 = new ListNode(4);
//        ListNode l23 = new ListNode(9);
//
//        l20.next = l21;
//        l21.next = l22;
//        l22.next = l23;

//        ListNode result = Leetcode.addTwoNumbers(l10, l20);
//        ListNode node = result;
//        while(node != null){
//            Log.e("TAG", "--"+ node.val);
//            node = node.next;
//        }

//        String s = "cabbad";
//        Leetcode.lengthOfLongestSubstring(s);
//        Leetcode.longestPalindrome(s);
//        int result = Leetcode.reverse(-1563847412);
//        Log.e("TAG", "" + Leetcode.isPalindrome(-2147447412));

//        int[] height = {1,2,3,4,5,6,7};
//        Log.e("TAG", Leetcode.maxArea(height)+"");

//        java.util.List<java.util.List<Integer>> res = Leetcode.threeSum(new int[]{-1,0,1,2,-1,-4});
//        int i = 0;

//        /**
//         * 图的邻接矩阵描述
//         */
//        Vertex[] vertexes = new Vertex[]{
//                new Vertex(0),
//                new Vertex(1),
//                new Vertex(2),
//                new Vertex(3),
//                new Vertex(4)
//
//        };
//
//        int[][] edges = new int[][]{
//                new int[]{0, 0, 1, 0, 6},
//                new int[]{0, 0, 3, 0, 1},
//                new int[]{1, 3, 0, 5, 0},
//                new int[]{0, 0, 5, 0, 1},
//                new int[]{6, 1, 0, 1, 0},
//        };
//        MatrixGraph graph = new MatrixGraph(vertexes, edges);
//        Log.e("TAG", "邻接矩阵深度遍历:");
//        graph.transverseDfs();
//        Log.e("TAG", "邻接矩阵广度遍历:");
//        graph.transverBfs();
//
//        /**
//         * 图的邻接表描述
//         */
//        VertexForEdge[] vertexForEdges = new VertexForEdge[]{
//                new VertexForEdge(0),
//                new VertexForEdge(1),
//                new VertexForEdge(2),
//                new VertexForEdge(3),
//                new VertexForEdge(4),
//        };
//
//        //0-> 2-4
//
//        int[][] edgesNodes = new int[][]{
//                new int[]{2, 4},
//                new int[]{2, 4},
//                new int[]{0, 1, 3},
//                new int[]{2, 4},
//                new int[]{0, 1, 3},
//        };
//
//        for (int i = 0; i < vertexForEdges.length; i++) {
//            VertexForEdge vertexForEdge = vertexForEdges[i];
//            Edge head = null, p = null;
//
//
//            int[] neighbours = edgesNodes[i];
//            for (int j = 0; j < neighbours.length; j++) {
//                Edge newNode = new Edge();
//                if(j == 0){//头结点初始化
//                    p = newNode;
//                    head = p;
//                }
//                newNode.weight = 1;
//                newNode.vertextIndex = neighbours[j];
//                if(j > 0) {
//                    p.next = newNode;
//                    p = p.next;
//                }
//            }
//            vertexForEdge.edgeHead = head;
//        }
//
//        EdgeListGraph edgeListGraph = new EdgeListGraph();
//        edgeListGraph.vertexes = vertexForEdges;
//        Log.e("TAG", "邻接表递归深度遍历:");
//        edgeListGraph.recursionDFS();
//        Log.e("TAG", "邻接表迭代深度遍历:");
//        edgeListGraph.iterationDFS();
//        Log.e("TAG", "邻接表迭代广度遍历:");
//        edgeListGraph.transverBfs();
    }
}
