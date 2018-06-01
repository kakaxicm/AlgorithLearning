package tree;

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
        return preOrderByRecursion(mRoot);
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

    @Override
    public String inOrder() {
        return null;
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
