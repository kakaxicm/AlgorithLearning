package tree.avltree;

import tree.BinaryNode;
import tree.Tree;

/**
 * Created by chenming on 2018/6/5
 */
public class AVLTree<T extends Comparable> implements Tree<T> {
    public AVLNode<T> mRoot;//为了测试用，mRoot先暴露出去

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
     * 删除操作
     *
     * @param data
     */
    @Override
    public void remove(T data) {
//        mRoot = remove(mRoot, data);
        mRoot = TestRemove(data, mRoot);
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
    public AVLNode<T> TestRemove(T data, AVLNode<T> rootNode) {
        if (data == null) {
            throw new RuntimeException("data can\'Comparable be null !");
        }

        if (rootNode == null) {
            return rootNode;//没有找到
        }

        int compareResult = data.compareTo(rootNode.data);
        if (compareResult < 0) {
            rootNode.left = TestRemove(data, rootNode.left);
        } else if (compareResult > 0) {
            rootNode.right = TestRemove(data, rootNode.right);
        } else if (rootNode.left != null && rootNode.right != null) {//俩孩子节点
            //右子树的最小值替换当前节点值
            rootNode.data = findMin(rootNode.right).data;
            //移除用于替换的点
            rootNode.right = TestRemove(rootNode.data, rootNode.right);
        } else {//只有一个孩子节点或者叶子节点的情况,次处返回的节点返回给上次递归，用于父节点链接该节点
            rootNode = (rootNode.left != null) ? rootNode.left : rootNode.right;
        }
        return rootNode;
    }


//    public AVLNode<T> remove(AVLNode<T> rootNode, T data) {
//        if (rootNode == null) {
//            return null;
//        }
//        //前驱节点和后继节点
//        AVLNode<T> pre;
//        AVLNode<T> post;
//
//        int compareResult = data.compareTo(rootNode.data);
//        if (compareResult == 0) {//找到节点
//            //待删除为叶子节点
//            if (rootNode.isLeaf()) {//待删除节点为叶子节点
//                rootNode = null;
//            } else if (rootNode.left == null) {//只有右孩子
//                rootNode = rootNode.right;
//            } else if (rootNode.right == null) {//只有左孩子
//                rootNode = rootNode.left;
//            } else {//既有左孩子又有右孩子
//                //当待删除结点左子树的高度大于右子树的高度时，用它的前驱结点pre代替它，
//                //再将结点pre从树中删除。这样可以保证删除结点后的树仍为二叉平衡树。
//                if (height(rootNode.left) > height(rootNode.right)) {
//                    pre = findMax(rootNode.left);//在左子树中寻找最大值作为前驱节点
//                    //pre元素替换
//                    rootNode.data = pre.data;
//                    //递归删除前驱节点
//                    rootNode.left = remove(rootNode.left, pre.data);
//                }
//                //当待删除结点左子树的高度小于或者等于右子树的高度时，用它的后继结点post代替它，
//                //再将结点post从树中删除。这样可以保证删除结点后的树仍为二叉平衡树。
//                else {
//                    //寻找后继节点post。
//                    post = findMin(rootNode.right);
//                    rootNode.data = post.data;
//                    rootNode.right = remove(rootNode.right, post.data);
//                }
//            }
//            return rootNode;
//        } else if (compareResult < 0) {//左子树递归删除
//            rootNode = remove(rootNode.left, data);
//            //删除后，修改树的高度
//            rootNode.height = Math.max(height(rootNode.left), height(rootNode.right)) + 1;
//            //左子树删除后，判断是否失衡
//            if (height(rootNode.right) - height(rootNode.left) == 2) {
//                //调整右子树
//                if (height(rootNode.right.left) > height(rootNode.right.right)) {
//                    //右子树的左子树导致失衡，则进行右左双旋转
//                    doubleRotateWithRight(rootNode);
//                } else {
//                    //右子树的右子树导致失衡，则进行右右单旋转
//                    singleRotateRight(rootNode);
//                }
//            }
//        } else {//右子树递归删除
//            rootNode = remove(rootNode.right, data);
//            //删除后，修改树的高度
//            rootNode.height = Math.max(height(rootNode.left), height(rootNode.right)) + 1;
//            //右子树删除后，判断rootNode是否失衡
//            if (height(rootNode.left) - height(rootNode.right) == 2) {
//                //调整右子树
//                if (height(rootNode.left.left) > height(rootNode.left.right)) {
//                    //左子树的左子树导致失衡，则进行左左单旋转
//                    singleRotateLeft(rootNode);
//                } else {
//                    //左子树的右子树导致失衡，则进行左右单旋转
//                    doubleRotateWithLeft(rootNode);
//                }
//            }
//        }
//        return rootNode;
//    }

    /**
     * 查找最小值结点
     *
     * @param p
     * @return
     */
    private AVLNode<T> findMin(AVLNode<T> p) {
        if (p == null)//结束条件
            return null;
        else if (p.left == null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.left);
    }

    /**
     * 查找最大值结点
     *
     * @param p
     * @return
     */
    private AVLNode<T> findMax(AVLNode<T> p) {
        if (p == null)
            return null;
        else if (p.right == null)//如果没有右结点,那么t就是最大的
            return p;
        return findMax(p.right);
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
