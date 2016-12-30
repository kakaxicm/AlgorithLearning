package tree;

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
     * @param data
     * @return
     */
    @Override
    public BinaryNode findNode(T data) {
        return findNode(data, mRoot);
    }

    /**
     * 递归查找元素
     * @param data
     * @param currentNode
     * @return
     */
    public BinaryNode findNode(T data, BinaryNode<T> currentNode){
        if(currentNode == null){
            return null;
        }

        int compareResult = data.compareTo(currentNode.data);
        if(compareResult == 0){
            return currentNode;
        }

        if(compareResult > 0){
            currentNode = findNode(data, currentNode.right);//移动node指针
        }else if(compareResult < 0){
            currentNode = findNode(data, currentNode.left);
        }

        return currentNode;
    }

    @Override
    public boolean contains(T data) {
        return contains(data, mRoot);
    }

    public boolean contains(T data, BinaryNode<T> tree){
        if(data == null){
            return false;
        }

        if(tree == null){
            return false;
        }

        int compareResult = data.compareTo(tree.data);
        if(compareResult == 0){
            return true;
        } else if(compareResult > 0){
           return contains(data, tree.right);
        }else if(compareResult < 0){
            return contains(data, tree.left);
        }
        return false;
    }

    /**
     * 前序遍历,先遍历节点,
     * @return
     */
    @Override
    public String preOrder() {
        return null;
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
    public void clear() {
        mRoot = null;
    }
}
