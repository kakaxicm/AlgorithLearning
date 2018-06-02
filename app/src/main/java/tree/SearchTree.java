package tree;

import queue.Queue;
import stack.Stack;

/**
 * Created by chenming on 2018/6/1
 * 排序二叉树树的复习,方法尽量提供循环和递归两种方式实现
 */
public class SearchTree<T extends Comparable> implements Tree<T> {

    private BinaryNode<T> mRoot;

    public SearchTree() {
        mRoot = null;
    }

    @Override
    public void insert(T data) {
//        insertByTrans(data);
        mRoot = insertByRecursion(mRoot, data);
    }

    /**
     * 循环插入
     */
    private void insertByTrans(T data) {
        BinaryNode<T> newNode = new BinaryNode<>(data);
        if (mRoot == null) {
            mRoot = newNode;
            return;
        }

        BinaryNode<T> curNode = mRoot;//从根节点出发
        BinaryNode<T> parentNode = mRoot;//当前节点的父节点
        boolean isLeft = false;//标记插入位置是左还是右
        while (curNode != null) {
            //循环体：判断大小
            parentNode = curNode;//保存父节点
            int compare = data.compareTo(curNode.data);
            if (compare > 0) {//data较大
                //curNode往右走
                isLeft = false;
                curNode = curNode.right;
            } else if (compare < 0) {
                //curNode往左走
                curNode = curNode.left;
                isLeft = true;
            } else {
                curNode.mDupCount++;
            }
        }

        if (isLeft) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }
    }

    /**
     * 递归插入元素
     *
     * @param node 子树节点
     * @param data 插入数据
     */
    private BinaryNode<T> insertByRecursion(BinaryNode<T> node, T data) {
        //递归结束条件 node == null表示已经到叶子节点的下面一层了，挂载新节点,返回
        if (node == null) {
            node = new BinaryNode<>(data);//连接新节点返回给上一层链接
        } else {//向左右子树递归
            //递归比较
            int compare = data.compareTo(node.data);

            if (compare < 0) {//左
                node.left = insertByRecursion(node.left, data);//由于插入操作需要链接节点,所以需要将低层次的node返回给上一层次的rootNode
            } else if (compare > 0) {//右
                node.right = insertByRecursion(node.right, data);
            } else {//相等
                node.mDupCount++;//重复计数+1
            }
        }

        return node;
    }

    @Override
    public BinaryNode findNode(T data) {
        return findNodeByTrans(data);
//        return findNodeByRecursion(mRoot, data);
    }

    /**
     * 循环查找
     *
     * @param data
     * @return
     */
    private BinaryNode findNodeByTrans(T data) {
        if (mRoot == null) {
            return null;
        }
        BinaryNode<T> curNode = mRoot;
        while (curNode != null) {
            int compare = data.compareTo(curNode.data);
            if (compare > 0) {//data较大
                //curNode往右走
                curNode = curNode.right;
            } else if (compare < 0) {
                //curNode往左走
                curNode = curNode.left;
            } else {//相等,则查找到
                return curNode;
            }
        }
        return null;
    }

    /**
     * 递归查找元素
     *
     * @param data
     * @return
     */
    private BinaryNode findNodeByRecursion(T data) {
        return findNodeByRecursion(mRoot, data);
    }

    /**
     * 当前子树节点
     *
     * @param node
     * @param data
     */
    public BinaryNode findNodeByRecursion(BinaryNode<T> node, T data) {
        //递归结束条件：子树节点为空 or node.data = data
        if (node == null) {
            return null;
        }
        int compare = data.compareTo(node.data);
        if (compare == 0) {//找到匹配元素,返回
            return node;
        }

        //否则向左右子树递归
        if (compare > 0) {//右子树递归
            return findNodeByRecursion(node.right, data);
        } else {
            return findNodeByRecursion(node.left, data);
        }
    }

    @Override
    public boolean isEmpty() {
        return mRoot == null;
    }

    /**
     * 前序遍历
     *
     * @return
     */
    @Override
    public String preOrder() {
//        return preOrderByRecursion(mRoot);
        return preOrderByTrans();
    }

    /**
     * 递归前序遍历：先访问根节点，在访问左右子树
     *
     * @param root
     * @return
     */
    private String preOrderByRecursion(BinaryNode<T> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {//递归结束条件
            sb.append(root.data + ", ");//先访问根节点
            sb.append(preOrderByRecursion(root.left));
            sb.append(preOrderByRecursion(root.right));
        }
        return sb.toString();
    }

    /**
     * 利用栈实现前序遍历,利用栈保存已经访问的节点，实现思想如下：
     * 1.当前节点p不为空时,访问节点，并保存入栈
     * 2.p节点向左遍历,执行1的操作,知道p为空
     * 3.p为空时，表示一条完整的路径已经遍历完,栈顶存放的是上一个节点即当前的父节点,弹出这个父节点，向它的右子树遍历,执行
     * p=stack.pop,p = p.right,然后重复1.2.3操作。
     * 当p==null && 栈为空时表示遍历完，退出循环
     *
     * @return
     */
    private String preOrderByTrans() {
        Stack<BinaryNode<T>> historyNodeStack = new Stack<>();
        BinaryNode<T> p = mRoot;
        StringBuilder result = new StringBuilder();
        while (p != null || !historyNodeStack.isEmpty()) {
            if (p == null) {//一条完整路径走到尽头,向父节点的右子树遍历
                p = historyNodeStack.pop();
                p = p.right;
            } else {
                //访问节点,保存路径,向左边遍历直到p=null
                result.append(p.data + ",");
                historyNodeStack.push(p);
                p = p.left;
            }
        }
        return result.toString();
    }

    /**
     * 中序遍历,遵从左中右的遍历顺序，实际上是按大小顺序输出！
     *
     * @return
     */
    @Override
    public String inOrder() {
//        return inOrderByRecursion(mRoot);
        return inOrderByTrans(mRoot);
    }

    /**
     * 递归中序遍历
     *
     * @return
     */
    public String inOrderByRecursion(BinaryNode root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {//递归结束条件
            sb.append(inOrderByRecursion(root.left));//先访问左子树
            sb.append(root.data + ", ");//访问根节点
            sb.append(inOrderByRecursion(root.right));//最后访问右子树
        }
        return sb.toString();
    }

    /**
     * 循环中序遍历,和前序引入栈存放经过的路径,这些路径没有被访问，当向左的路径走到尽头，弹栈访问节点，并向右遍历，
     * 如果p不为空，则存入stack，向左边遍历
     *
     * @return
     */
    public String inOrderByTrans(BinaryNode root) {
        Stack<BinaryNode<T>> historyNodeStack = new Stack<>();
        BinaryNode<T> p = mRoot;
        StringBuilder result = new StringBuilder();
        while (p != null || !historyNodeStack.isEmpty()) {
            if (p == null) {//一条完整路径走到尽头,弹栈，并访问它
                p = historyNodeStack.pop();
                result.append(p.data + ",");
                p = p.right;
            } else {
                //向左边遍历直到p=null
                historyNodeStack.push(p);
                p = p.left;
            }
        }
        return result.toString();
    }

    @Override
    public String postOrder() {
//        return postOrderByRecursion(mRoot);
        return postOrderByTrans();
    }

    /**
     * 递归后序遍历
     *
     * @return
     */
    public String postOrderByRecursion(BinaryNode root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {//递归结束条件
            sb.append(postOrderByRecursion(root.left));//先访问左子树
            sb.append(postOrderByRecursion(root.right));//最后访问右子树
            sb.append(root.data + ", ");//访问根节点
        }
        return sb.toString();
    }

    /**
     * 循环实现后序遍历
     *
     * @return
     */
    public String postOrderByTrans() {
        Stack<BinaryNode<T>> stack = new Stack<>();
        BinaryNode<T> currentNode = mRoot;
        BinaryNode<T> prev = mRoot;
        StringBuilder result = new StringBuilder();
        while (currentNode != null || !stack.isEmpty()) {
            //把左子树加入栈中,直到叶子结点为止,这是左子树先遍历的前提
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty()) {//当前的栈顶节点未访问，先看看它的右节点是否被访问过或者是否为空，如果是则表示右子树遍历完，可以弹出访问
                BinaryNode<T> rightNode = stack.peek().right;
                if (rightNode == null || rightNode == prev) {//右子树访问完毕，才弹出父节点
                    currentNode = stack.pop();//弹出父节点访问
                    result.append(currentNode.data + ",");
                    prev = currentNode;
                    currentNode = null;//curnode=null，避免上面的while重复循环
                } else {
                    currentNode = rightNode;//向右遍历
                }

            }
        }
        return result.toString();
    }


    /**
     * 程序遍历:二叉树的兄弟节点没有直接联系，无法用递归实现,引入队列
     *
     * @return
     */
    @Override
    public String levelOrder() {
        Queue<BinaryNode<T>> queue = new Queue<>();
        StringBuilder result = new StringBuilder();
        BinaryNode<T> p = mRoot;
        while (p != null) {
            result.append(p.data + ",");
            //左右子节点入队
            if (p.left != null) {
                queue.enquene(p.left);
            }

            //左右子节点入队
            if (p.right != null) {
                queue.enquene(p.right);
            }

            p = queue.dequeue();

        }
        return result.toString();
    }

    @Override
    public int size() {
        return size(mRoot);
    }

    /**
     * 递归实现大小计算
     *
     * @param root
     * @return
     */
    public int size(BinaryNode<T> root) {
        if (root == null) {
            return 0;
        }

        return size(root.left) + 1 + size(root.right);
    }

    @Override
    public int height() {
        return height(mRoot);
    }

    /**
     * 递归求高度
     *
     * @param root
     * @return
     */
    public int height(BinaryNode<T> root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return (lh > rh) ? lh + 1 : rh + 1;
    }

    @Override
    public void remove(T data) {
//        removeByRecursion(data, mRoot);
        removeByTrans(data);
    }

    /**
     * 递归删除元素
     * 1.当找到目标节点时，分三种情况删除元素
     * 1>叶子节点，直接删除
     * 2>带一个子节点,父节点指向它的子节点
     * 3>带俩节点，找到右子树的最小元素(中继节点)，替换当前节点,并递归删除右子树的这个中继节点
     *
     * @param data
     * @param p
     * @return
     */
    public BinaryNode<T> removeByRecursion(T data, BinaryNode<T> p) {
        if (p == null) {
            return null;//没有找到元素,返回空
        }

        int compareResult = data.compareTo(p.data);
        if (compareResult < 0) {//左边查找删除结点
            //左边相当于父节点，右边相当于删除节点后的子节点
            p.left = removeByRecursion(data, p.left);
        } else if (compareResult > 0) {
            p.right = removeByRecursion(data, p.right);
        } else {
            //找到目标节点，删除分三种情况
            if (p.left != null && p.right != null) {
                //情况3 找到中继节点,替换元素，删除它
                p.data = findMinByTrans(p.right);//找到中继节点
                p.right = removeByRecursion(p.data, p.right);//右子树删除这个中继节点
            } else {//情况1和2，遍历到最下面了
                p = (p.left != null) ? p.left : p.right;
            }
        }
        return p;//返回删除后的子树节点,用于返回上层递归连接父节点
    }

    /**
     * 循环删除
     *
     * @param data
     * @return
     */
    public BinaryNode<T> removeByTrans(T data) {
        //找到目标节点
        if (data == null) {
            return null;
        }

        BinaryNode<T> current = mRoot;
        BinaryNode<T> parent = mRoot;//记录父节点
        //判断左右孩子的flag
        boolean isLeft = true;

        //找到要删除的结点
        while (data.compareTo(current.data) != 0) {
            //更新父结点记录
            parent = current;
            int result = data.compareTo(current.data);

            if (result < 0) {//从左子树查找
                isLeft = true;
                current = current.left;
            } else if (result > 0) {//从右子树查找
                isLeft = false;
                current = current.right;
            }
            //如果没有找到,返回null
            if (current == null) {
                return null;
            }
        }

        //找到目标位置,判断它的挂载情况
        //删除叶子节点
        if (current.left == null && current.right == null) {
            if (current == mRoot) {
                mRoot = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }

        } else if (current.left == null) {//right不为空
            if (current == mRoot) {
                mRoot = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {//left不为空
            if (current == mRoot) {
                mRoot = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {//带有俩孩子的节点
            //找右边子树的最小节点（中继节点）
            //找到当前要删除结点current的右子树中的最小值元素
            BinaryNode<T> successor = findRelayNode(current);//找到中继节点，并且中继节点的右边子树已经指向了要删除节点的右子树
            if (current == mRoot) {
                mRoot = successor;
            } else if (isLeft) {
                parent.left = successor;//左子树连接中继节点
            } else {
                parent.right = successor;//右子树连接中继节点
            }
            //把当前要删除的结点的左子树赋值给successor的左子树
            successor.left = current.left;

        }
        return current;
    }

    /**
     * 查找中继节点
     *
     * @param delNode 要删除的节点
     * @return
     */
    private BinaryNode<T> findRelayNode(BinaryNode<T> delNode) {
        BinaryNode<T> relayNode = delNode;//最小节点
        BinaryNode<T> relayParentNode = delNode;//父节点
        BinaryNode<T> curNode = delNode.right;//临时遍历变量
        while (curNode != null) {
            relayParentNode = relayNode;//保存父节点
            relayNode = curNode;
            curNode = curNode.left;
        }

        if (relayNode != delNode.right) {
            //如果relayNode不是删除节点的直接子节点，relayNode就是要替换delNode的节点,
            // 第一步先处理好它的断开操作:relayNode是最小节点，所以它必然在父节点的左边，且没有左子节点
            // 所以relayParentNode的左子树指向relayNode的右子树,即可完成relayNode的断开操作
            // 断开操作之后,relayNode需要替代delNode,需要把delNode的右边子树赋给relayNode.right
            relayParentNode.left = relayNode.right;
            //这种情况下relayNode和delNode至少隔了一层，所以需要将delNode的右边子树赋值给relayNode的右子树，
            relayNode.right = delNode.right;
        }//relayNode == delNode.right时候，说明delNode和relayNode是直接的父子关系，不用额外操作

        return relayNode;

    }

    @Override
    public T findMin() {
//        return findMinByTrans(mRoot);
        return findMinRecursion(mRoot);
    }

    /**
     * 循环查找最小值
     *
     * @param root
     * @return
     */
    public T findMinByTrans(BinaryNode<T> root) {
        BinaryNode<T> p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p.data;
    }

    /**
     * 递归查找最小值
     *
     * @param root
     * @return
     */
    public T findMinRecursion(BinaryNode<T> root) {
        if (root.left == null) {
            return root.data;
        }

        return findMinRecursion(root.left);
    }

    /**
     * 循环查找最小值
     *
     * @param root
     * @return
     */
    public T findMaxByTrans(BinaryNode<T> root) {
        BinaryNode<T> p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p.data;
    }

    /**
     * 递归查找最小值
     *
     * @param root
     * @return
     */
    public T findMaxRecursion(BinaryNode<T> root) {
        if (root.right == null) {
            return root.data;
        }

        return findMaxRecursion(root.right);
    }


    @Override
    public T findMax() {
        return findMaxRecursion(mRoot);
//        return findMaxByTrans(mRoot);
    }

    /**
     * 是否包含元素
     *
     * @param data
     * @return
     * @throws Exception
     */
    @Override
    public boolean contains(T data) {
//        return containsByRecursion(data, mRoot);
        return containsByTrans(data);
    }

    /***
     * 递归判断是否包含元素
     * @param data
     * @param tree
     * @return
     */
    public boolean containsByRecursion(T data, BinaryNode<T> tree) {
        if (data == null) {
            return false;
        }

        if (tree == null) {//遍历到叶子节点的下一层，表示遍历完毕，递归结束
            return false;
        }

        int compareResult = data.compareTo(tree.data);
        if (compareResult == 0) {//相等则返回ture
            return true;
        } else if (compareResult > 0) {
            return containsByRecursion(data, tree.right);//向右子树遍历
        } else if (compareResult < 0) {
            return containsByRecursion(data, tree.left);//向右左子树遍历
        }
        return false;
    }

    /**
     * 循环判断是否包含某个元素
     *
     * @param data
     * @return
     */
    public boolean containsByTrans(T data) {
        BinaryNode<T> p = mRoot;
        while (p != null) {
            int compareResult = data.compareTo(p.data);
            if (compareResult == 0) {
                return true;
            } else if (compareResult > 0) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        mRoot = null;
    }

    /**
     * 根据前序遍历和中序遍历数组构建二叉树
     *
     * @param preList
     * @param inList
     * @param preStart
     * @param preEnd
     * @param inStart
     * @param inEnd
     * @return
     */
    public BinaryNode<T> creatTreeByPreIn(
            T[] preList,
            T[] inList,
            int preStart,
            int preEnd,
            int inStart,
            int inEnd) {
        BinaryNode<T> p = new BinaryNode<>(preList[preStart]);
        if (preStart == preEnd && inStart == inEnd){
            return p;
        }
        //找到根节点在中序列表中的位置
        int rootIndex = 0;
        for(rootIndex = inStart; rootIndex < inEnd; rootIndex ++ ){
            if(preList[preStart].compareTo(inList[rootIndex]) == 0){
                break;
            }
        }
        int leftLen = rootIndex - inStart;
        int rightLen = inEnd - rootIndex;
        //递归构建
        if(leftLen > 0){
            //左子树的先根序列：preList[1] , ... , preList[i]
            //左子树的中根序列：inList[0] , ... , inList[i-1]
            p.left = creatTreeByPreIn(preList, inList,
                    preStart+1,
                    preStart+leftLen,
                     inStart,
                    rootIndex - 1);
        }
        if(rightLen > 0){
            //右子树的先根序列：preList[preStart+1+leftLen] , ... , preList[n-1]
            //右子树的中根序列：inList[i+1] , ... , inList[n-1]
            p.right = creatTreeByPreIn(preList, inList,
                    preStart+1+leftLen,
                    preEnd, rootIndex+1, inEnd);
        }
        return p;
    }

    /**
     * 根据前序和中序构建二叉树
     * @param preList
     * @param inList
     * @return
     */
    public SearchTree<T> buildTreeByPreIn(T[] preList,
                                          T[] inList){
        BinaryNode<T> root = creatTreeByPreIn(preList,inList, 0, preList.length -1, 0,inList.length-1);
        mRoot = root;
        return this;
    }

    /**
     * 根据后序和中序遍历数组构建二叉树
     * @param postList
     * @param inList
     * @param postStart
     * @param postEnd
     * @param inStart
     * @param inEnd
     * @return
     */
    public BinaryNode<T> createBinarySearchTreeByPostIn(T[] postList,T[] inList,int postStart,int postEnd,int inStart,int inEnd){

        //构建根结点
        BinaryNode<T> p=new BinaryNode<>(postList[postEnd]);

        if(postStart==postEnd && inStart==inEnd){
            return p;
        }

        //查找中根序列的根结点下标root
        int root=0;

        for (root=inStart;root<inEnd;root++){
            //查找到
            if (postList[postEnd].compareTo(inList[root])==0){
                break;
            }
        }

        //左子树的长度
        int leftLenght=root-inStart;
        //右子树的长度
        int rightLenght=inEnd-root;

        //递归构建左子树
        if(leftLenght>0){
            //postStart+leftLenght-1:后根左子树的结束下标
            p.left=createBinarySearchTreeByPostIn(postList,inList,postStart,postStart+leftLenght-1,inStart,root-1);
        }

        //递归构建右子树
        if(rightLenght>0){
            p.right=createBinarySearchTreeByPostIn(postList,inList,postStart+leftLenght,postEnd-1,root+1,inEnd);
        }

        return p;
    }

    /**
     * 根据后序和中序构建二叉树
     * @param preList
     * @param inList
     * @return
     */
    public SearchTree<T> buildTreeByPostIn(T[] preList,
                                          T[] inList){
        BinaryNode<T> root = createBinarySearchTreeByPostIn(preList,inList, 0, preList.length -1, 0,inList.length-1);
        mRoot = root;
        return this;
    }

}
