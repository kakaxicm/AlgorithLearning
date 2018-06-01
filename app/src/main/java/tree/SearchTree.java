package tree;

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
     p=stack.pop,p = p.right,然后重复1.2.3操作。
     *当p==null && 栈为空时表示遍历完，退出循环
     * @return
     */
    private String preOrderByTrans(){
        Stack<BinaryNode<T>> historyNodeStack = new Stack<>();
        BinaryNode<T> p = mRoot;
        StringBuilder result = new StringBuilder();
        while (p != null || !historyNodeStack.isEmpty()){
            if(p == null){//一条完整路径走到尽头,向父节点的右子树遍历
                p = historyNodeStack.pop();
                p = p.right;
            }else{
                //访问节点,保存路径,向左边遍历直到p=null
                result.append(p.data+",");
                historyNodeStack.push(p);
                p = p.left;
            }
        }
        return result.toString();
    }

    /**
     * 中序遍历
     * @return
     */
    @Override
    public String inOrder() {
        return inOrderByRecursion(mRoot);
    }

    /**
     * 递归中序遍历
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

    @Override
    public String postOrder() {
        return null;
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }


    @Override
    public void remove(T data) {

    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public T findMax() {
        return null;
    }

    /**
     * 是否包含元素
     *
     * @param data
     * @return
     * @throws Exception
     */
    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }
}
