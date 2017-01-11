package tree;

import java.util.HashMap;

import List.Node;
import queue.Queue;
import stack.Stack;

/**
 * Created by chenming on 16/12/30.
 */

public class BinaryTree<T extends Comparable> implements Tree<T> {
    //根结点
    public BinaryNode<T> mRoot;

    public BinaryTree() {
        mRoot = null;
    }

    /**
     * 新加元素
     *
     * @param data
     */
    @Override
    public void insert(T data) {
        mRoot = insertNode(data, mRoot);
    }

    /**
     * 递归插入:
     * 1.如果当前节点为空，则直接加入新节点,递归返回
     * 2.比较当前节点的值，向右或向左遍历
     *
     * @param data
     * @param rootNode 当前递归操作节点
     * @return 返回当前递归插入的根节点
     */
    private BinaryNode<T> insertNode(T data, BinaryNode<T> rootNode) {
        if (data == null) {
            throw new RuntimeException("data can\'Comparable be null !");
        }

        BinaryNode<T> newNode = new BinaryNode<>(data, null, null);

        if (rootNode == null) {
            rootNode = newNode;
        } else {
            //递归比较
            int compare = data.compareTo(rootNode.data);

            if (compare < 0) {//左
                rootNode.left = insertNode(data, rootNode.left);//由于插入操作需要链接jiedian,所以需要将低层次的node返回给上一层次的rootNode
            } else if (compare > 0) {//右
                rootNode.right = insertNode(data, rootNode.right);
            } else {//相等
                rootNode.mDupCount++;//重复计数+1
            }
        }
        return rootNode;
    }

    /**
     * 最小值
     *
     * @return
     */
    @Override
    public T findMin() {
        return findMin(mRoot);
    }

    /**
     * 递归像左边查找
     *
     * @param root 根节点
     * @return
     */
    public T findMin(BinaryNode<T> root) {
        if (root == null) {
            return null;
        }

        if (root.left == null) {//遍历到最左边，返回
            return root.data;
        }

        return findMin(root.left);
    }

    @Override
    public T findMax() {
        return findMax(mRoot);
    }

    /**
     * 递归像左边查找
     *
     * @param root
     * @return
     */
    public T findMax(BinaryNode<T> root) {
        if (root == null) {
            return null;
        }

        if (root.right == null) {//遍历到最右边，返回
            return root.data;
        }

        return findMax(root.right);
    }

    @Override
    public void remove(T data) {
        mRoot = remove(data, mRoot);
    }

    /**
     * 删除步骤:
     * ① 如果要删除的结点q恰好是叶子结点，那么它可以立即被删除
     * ②如果要删除的结点q拥有一个孩子结点，则应该调整要被删除的
     * 父结点(p.left 或 p.right)指向被删除结点的孩子结点（q.left 或 q.right）
     * ③如果要删除的结点q拥有两个孩子结点，则删除策略是用q的右子树的最小的数据替代要被删除结点的数据，
     * 并递归删除用于替换的结点(此时该结点已为空)，此时二叉查找树的结构并不会被打乱，其特性仍旧生效。
     * 采用这样策略的主要原因是右子树的最小结点的数据替换要被删除的结点后可以满足维持二叉查找树的结构和特性，
     * 又因为右子树最小结点不可能有左孩子，删除起来也相对简单些。
     *
     * @param data
     * @param rootNode 当前操作节点
     * @return
     */
    public BinaryNode<T> remove(T data, BinaryNode<T> rootNode) {
        if (data == null) {
            throw new RuntimeException("data can\'Comparable be null !");
        }

        if (rootNode == null) {
            return rootNode;//没有找到
        }

        int compareResult = data.compareTo(rootNode.data);
        if (compareResult < 0) {
            rootNode.left = remove(data, rootNode.left);
        } else if (compareResult > 0) {
            rootNode.right = remove(data, rootNode.right);
        } else if (rootNode.left != null && rootNode.right != null) {//俩孩子节点
            //右子树的最小值替换当前节点值
            rootNode.data = findMin(rootNode.right);
            //移除用于替换的点
            rootNode.right = remove(rootNode.data, rootNode.right);
        } else {//只有一个孩子节点或者叶子节点的情况,次处返回的节点返回给上次递归，用于父节点链接该节点
            rootNode = (rootNode.left != null) ? rootNode.left : rootNode.right;
        }
        return rootNode;
    }

    @Override
    public boolean isEmpty() {
        return mRoot == null;
    }

    /**
     * 深度
     *
     * @return
     */
    @Override
    public int height() {
        return height(mRoot);
    }

    /**
     * 递归计算左右两颗子树的深度,取较大值+1
     *
     * @return
     */
    public int height(BinaryNode<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftHeight = height(currentNode.left);
        int rightHeight = height(currentNode.right);

        return leftHeight > rightHeight ? (leftHeight + 1) : (rightHeight + 1);//加上当前的层数
    }

    @Override
    public int size() {
        return size(mRoot);
    }

    /**
     * 递归计算左右子树的和,再加上当前节点
     *
     * @param currentNode
     * @return
     */
    public int size(BinaryNode<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }
        int leftSize = size(currentNode.left);
        int rightSize = size(currentNode.right);
        return leftSize + rightSize + 1;
    }

    /**
     * 查找节点
     *
     * @param data
     * @return
     */
    @Override
    public BinaryNode findNode(T data) {
        return findNode(data, mRoot);
    }

    /**
     * 递归查找元素
     *
     * @param data
     * @param currentNode
     * @return
     */
    public BinaryNode findNode(T data, BinaryNode<T> currentNode) {
        if (currentNode == null) {
            return null;
        }

        int compareResult = data.compareTo(currentNode.data);
        if (compareResult == 0) {
            return currentNode;
        }

        if (compareResult > 0) {
            currentNode = findNode(data, currentNode.right);//移动node指针
        } else if (compareResult < 0) {
            currentNode = findNode(data, currentNode.left);
        }

        return currentNode;
    }

    @Override
    public boolean contains(T data) {
        return contains(data, mRoot);
    }

    public boolean contains(T data, BinaryNode<T> tree) {
        if (data == null) {
            return false;
        }

        if (tree == null) {
            return false;
        }

        int compareResult = data.compareTo(tree.data);
        if (compareResult == 0) {
            return true;
        } else if (compareResult > 0) {
            return contains(data, tree.right);
        } else if (compareResult < 0) {
            return contains(data, tree.left);
        }
        return false;
    }

    /**
     * 前序遍历,先遍历节点,再遍历左子树，最后遍历右子树
     *
     * @return
     */
    @Override
    public String preOrder() {
        return preOrder(mRoot);
    }

    /**
     * 前序遍历,先遍历节点,再遍历左子树，最后遍历右子树
     *
     * @return
     */
    public String preOrder(BinaryNode<T> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.data + ", ");
            sb.append(preOrder(root.left));
            sb.append(preOrder(root.right));
        }
        return sb.toString();
    }

    /**
     * 中序列遍历,先遍历左子树,再遍历根节点, 最后遍历右子树,在有序查找二叉树的中序遍历就是依次排序输出
     *
     * @return
     */
    @Override
    public String inOrder() {
        return inOrder(mRoot);
    }

    /**
     * 递归中序遍历
     *
     * @param root
     * @return
     */
    public String inOrder(BinaryNode<T> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(inOrder(root.left));
            sb.append(root.data + ", ");
            sb.append(inOrder(root.right));
        }
        return sb.toString();
    }

    @Override
    public String postOrder() {
        return postOrder(mRoot);
    }

    /**
     * 递归中序遍历
     *
     * @param root
     * @return
     */
    public String postOrder(BinaryNode<T> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(postOrder(root.left));
            sb.append(postOrder(root.right));
            sb.append(root.data + ", ");
        }
        return sb.toString();
    }

    /**
     * 层遍历:关键在于队列保存节点的两个孩子节点,每次迭代做如下操作:
     * 1.将非空子节点入队
     * 2.输出队列头节点
     *
     * @return
     */
    @Override
    public String levelOrder() {
        return levelOrder(mRoot);
    }

    /**
     * 层遍历
     *
     * @param node
     * @return
     */
    public String levelOrder(BinaryNode<T> node) {
        StringBuilder sb = new StringBuilder();
        Queue<BinaryNode<T>> queue = new Queue<>();
        while (node != null) {
            sb.append(node.data + ", ");
            BinaryNode<T> leftNode = node.left;
            BinaryNode<T> rightNode = node.right;
            //同层的子节点入队
            if (leftNode != null) {
                queue.enquene(leftNode);
            }
            if (rightNode != null) {
                queue.enquene(rightNode);
            }

            node = queue.dequeue();//遍历下个节点

        }

        return sb.toString();
    }

    /**
     * 根据前序和中序的结果，构造对应的二叉树,步骤如下:
     * 1.前序数组的第一个元素为根节点,找到它在中序数组中的位置
     * 2.根据中序数组计算左右子树的长度,且中序数组中的左右子树片段也已确定
     * 3.根据左右子树的长度，在前序数组中找到对应左右子树的片段
     * 4.确定了左右子树的
     *
     * @param preArray    前序结果
     * @param inListArray 中序结果
     * @param preStart    当前迭代的前序索引起始点
     * @param preEnd      当前迭代的前序索引结束点
     * @param inStart     当前迭代的中序索引起始点
     * @param inEnd       当前迭代的中序索引结束点
     * @return
     */
    public BinaryNode<T> createBinaryTreeByPreIn(T[] preArray, T[] inListArray, int preStart, int preEnd, int inStart, int inEnd) {
        //preList[preStart]必须根结点数据,创建根结点root
        BinaryNode<T> root = new BinaryNode<>(preArray[preStart]);
        //递归结束条件
        if (preStart == preEnd && inStart == inEnd) {
            return root;
        }

        int rootIndex;
        //查找中序数组中根节点索引
        for (rootIndex = inStart; rootIndex < inEnd; rootIndex++) {
            T item = inListArray[rootIndex];
            if (item.compareTo(root.data) == 0) {
                break;
            }
        }

        //计算左右子树的长度
        int leftLen = rootIndex - inStart;
        int rightLen = inEnd - rootIndex;

        //左子树中序片段: inStart--> rootIndex-1
        //右子树中序片段: rootIndex+1 --> inEnd
        //左子树前序片段: preStart+1 --> preStart + leftLen
        //右子树前序片段: preStart+ leftLen + 1 --> preEnd

        //构建左右子树
        if (leftLen > 0) {
            root.left = createBinaryTreeByPreIn(preArray, inListArray, preStart + 1, preStart + leftLen, inStart, rootIndex - 1);
        }

        if (rightLen > 0) {
            root.right = createBinaryTreeByPreIn(preArray, inListArray, preStart + leftLen + 1, preEnd, rootIndex + 1, inEnd);
        }

        return root;
    }

    @Override
    public void clear() {
        mRoot = null;
    }

    /**
     * 根据优先级列表创建赫夫曼二叉树：
     * 根据前两个节点,构造子树，然后与后面的节点合并成新的子树,挂载节点原则:
     *
     *
     * @param priorityArr 元素权重递减的数组
     * @return 赫夫曼根节点
     */
    public static BinaryNode<HuffmanModel> createHuffmanTree(Integer[] priorityArr) {
        Stack<BinaryNode<HuffmanModel>> stack = new Stack<>();
        BinaryNode<HuffmanModel> newRootNode = null;//新建节点，连接新节点和之前生成的子树
        if (priorityArr == null || priorityArr.length == 0) {
            return newRootNode;
        }

        int i = 0;
        //两两合并节点，构建子树入栈
        while (i < priorityArr.length) {
            BinaryNode<HuffmanModel> newNode = new BinaryNode<>(new HuffmanModel(priorityArr[i]));//叶子节点
            if (i == 0) {//第一个节点,无法构建子树,直接入栈
                stack.push(newNode);
                i++;
                continue;
            }
            //每一次将上次构建的子树和新的节点合并成新子树入栈
            BinaryNode<HuffmanModel> tempRootNode = new BinaryNode<>(new HuffmanModel(0));//挂载叶子节点的新的根节点
            BinaryNode<HuffmanModel> currentSubTree = stack.pop();
            //合并节点
            if(newNode.data.weight > currentSubTree.data.weight){
                tempRootNode.left = currentSubTree;
                tempRootNode.right = newNode;
            }else {
                tempRootNode.left = newNode;
                tempRootNode.right = currentSubTree;
            }

            /**
             * 更新权重,权重为节点左右
             */
            if (tempRootNode.left != null) {
                tempRootNode.data.weight += tempRootNode.left.data.weight;
            }

            if (tempRootNode.right != null) {
                tempRootNode.data.weight += tempRootNode.right.data.weight;
            }

            stack.push(tempRootNode);
            i++;
        }

        newRootNode = stack.pop();

        return newRootNode;
    }

    /**
     * 赫夫曼编码
     * 每一次递归都基于父节点的code
     */
    public static void huffmanEnCodeNode(BinaryNode<HuffmanModel> node, HashMap<String, Integer> codeTable){
        if(node.isLeaf()){//到叶子节点了，递归结束
            codeTable.put(node.data.code, node.data.data);//赫夫曼数的编码数据都在叶子节点, 保存编码表
            return;
        }
        /**
         * 向左遍历
         */

        if(node.left != null){
            StringBuilder sb = new StringBuilder();
            sb.append(node.data.code).append("0");
            node.left.data.code = sb.toString();//子节点编码
            huffmanEnCodeNode(node.left, codeTable);
        }

        /**
         * 向右遍历
         */
        if(node.right != null){
            StringBuilder sb = new StringBuilder();
            sb.append(node.data.code).append("1");
            node.right.data.code = sb.toString();//子节点编码
            huffmanEnCodeNode(node.right, codeTable);
        }
    }
}
