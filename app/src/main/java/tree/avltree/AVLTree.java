package tree.avltree;

import tree.BinaryNode;
import tree.Tree;

/**
 * Created by chenming on 2018/6/5
 */
public class AVLTree<T extends Comparable> implements Tree<T> {
    AVLNode<T> mRoot;

    /**
     * 向左子树的左节点插入元素,执行右旋操作
     *
     * @param x
     * @return
     */
    private AVLNode<T> singleRotateLeft(AVLNode<T> x) {
        AVLNode<T> w = x.left;
        x.left = w.right;
        w.right = x;
        //重新计算x w的高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        w.height = Math.max(height(w.left), x.height) + 1;
        return w;
    }

    /**
     * 向右子树的右节点插入元素,执行左旋操作
     *
     * @param w
     * @return
     */
    private AVLNode<T> singleRotateRight(AVLNode<T> w) {
        AVLNode<T> x = w.right;

        w.right = x.left;
        x.left = w;

        //重新计算x/w的高度
        w.height = Math.max(height(w.left), height(w.right)) + 1;
        x.height = Math.max(height(x.left), w.height) + 1;

        //返回新的根结点
        return x;
    }

    /**
     * 往左子树的右孩子插入节点,左右双旋
     */
    private AVLNode<T> doubleRotateWithLeft(AVLNode<T> x) {
        //w先进行右右单旋转
        x.left = singleRotateRight(x.left);
        return singleRotateLeft(x);
    }

    /**
     * 右左旋转(RL旋转)
     *
     * @param x
     * @return
     */
    private AVLNode<T> doubleRotateWithRight(AVLNode<T> x) {
        //先进行LL旋转
        x.right = singleRotateLeft(x.right);
        //再进行RR旋转
        return singleRotateRight(x);
    }

    @Override
    public void insert(T data) {
        //TODO
        mRoot = insert(data, mRoot);
    }

    /**
     * 平衡二叉树的插入操作
     *
     * @param data
     * @param p
     * @return
     */
    private AVLNode<T> insert(T data, AVLNode<T> p) {
        if (p == null) {
            p = new AVLNode<>(data);
        } else if (data.compareTo(p.data) < 0) {//向左子树寻找插入位置{
            p.left = insert(data, p.left);
            if (height(p.left) - height(p.right) == 2) {//子树高度差为2,需要平衡
                if (data.compareTo(p.left.data) < 0) {//往左边插入LL单个旋转
                    p = singleRotateLeft(p);
                } else {
                    p = doubleRotateWithLeft(p);
                }
            }
        } else if (data.compareTo(p.data) > 0) {//向右子树寻找插入位置
            p.right = insert(data, p.right);

            if (height(p.right) - height(p.left) == 2) {
                if (data.compareTo(p.right.data) < 0) {
                    //进行右左旋转
                    p = doubleRotateWithRight(p);
                } else {
                    p = singleRotateRight(p);
                }
            }
        }
        //重新计算节点高度
        if (p != null) {
            p.height = Math.max(height(p.left), height(p.right)) + 1;
        }
        return p;
    }

    /**
     * @param p
     * @return
     */
    public int height(AVLNode<T> p) {
        return p == null ? -1 : p.height;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return height(mRoot);
    }

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

    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }
}
