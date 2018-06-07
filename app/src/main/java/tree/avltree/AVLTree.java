package tree.avltree;

import queue.Queue;
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
                if (data.compareTo(p.left.data) < 0) {//往左边插入LL单旋转
                    p = singleRotateLeft(p);
                } else {
                    p = doubleRotateWithLeft(p);//往左边插入LR双旋转
                }
            }
        } else if (data.compareTo(p.data) > 0) {//向右子树寻找插入位置
            p.right = insert(data, p.right);

            if (height(p.right) - height(p.left) == 2) {
                if (data.compareTo(p.right.data) < 0) {
                    //进行RL双旋转
                    p = doubleRotateWithRight(p);
                } else {
                    //进行RR单旋
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
        mRoot = removeAvlNode(data, mRoot);
    }

    /**
     * 非平衡二叉树的删除步骤:
     * ① 如果要删除的结点q恰好是叶子结点，那么它可以立即被删除
     * ②如果要删除的结点q拥有一个孩子结点，则应该调整要被删除的
     * 父结点(p.left 或 p.right)指向被删除结点的孩子结点（q.left 或 q.right）
     * ③如果要删除的结点q拥有两个孩子结点，则删除策略是用q的右子树的最小的数据替代要被删除结点的数据，
     * 并递归删除用于替换的结点(此时该结点已为空)，此时二叉查找树的结构并不会被打乱，其特性仍旧生效。
     * 采用这样策略的主要原因是右子树的最小结点的数据替换要被删除的结点后可以满足维持二叉查找树的结构和特性，
     * 又因为右子树最小结点不可能有左孩子，删除起来也相对简单些。
     * 平衡二叉树的删除方法在之前的基础上需要考虑下面两点
     * ① 当前待删除节点左子树高度大于右子树高度，则找在左子树中寻找前驱节点替换当前节点
     * ② 删除操作执行后，别忘了重新更新根节点高度，然后根据左右子树的高度判断平衡性，根据前面分析的四种情况进行旋转
     *
     * @param data
     * @param rootNode 当前操作节点
     * @return
     */
    public AVLNode<T> removeAvlNode(T data, AVLNode<T> rootNode) {
        if (data == null) {
            return rootNode;
        }

        if (rootNode == null) {
            return rootNode;//没有找到
        }

        int compareResult = data.compareTo(rootNode.data);
        if (compareResult < 0) {//左子树递归删除
            rootNode.left = removeAvlNode(data, rootNode.left);
            // 高度计算，判断是否失衡
            //删除后，修改树的高度,左子树删除，
            rootNode.height = Math.max(height(rootNode.left), height(rootNode.right)) + 1;
            //左子树删除后，判断是否失衡
            if (height(rootNode.right) - height(rootNode.left) == 2) {
                //调整右子树
                if (height(rootNode.right.left) > height(rootNode.right.right)) {
                    //右子树的左子树导致失衡，则进行右左双旋转
                    rootNode = doubleRotateWithRight(rootNode);
                } else {
                    //右子树的右子树导致失衡，则进行右右单旋转
                    rootNode = singleRotateRight(rootNode);
                }
            }
        } else if (compareResult > 0) {//右子树递归删除
            rootNode.right = removeAvlNode(data, rootNode.right);
            //高度计算，判断是否失衡
            //删除后，修改树的高度
            rootNode.height = Math.max(height(rootNode.left), height(rootNode.right)) + 1;
            //右子树删除后，判断rootNode是否失衡
            if (height(rootNode.left) - height(rootNode.right) == 2) {
                //调整右子树
                if (height(rootNode.left.left) > height(rootNode.left.right)) {
                    //左子树的左子树导致失衡，则进行左左单旋转
                    rootNode = singleRotateLeft(rootNode);
                } else {
                    //左子树的右子树导致失衡，则进行左右单旋转
                    rootNode = doubleRotateWithLeft(rootNode);
                }
            }
        } else if (rootNode.left != null && rootNode.right != null) {//俩孩子节点
            //当待删除结点左子树的高度大于右子树的高度时，用*T的前驱结点pre代替*T，
            //再将结点pre从树中删除。这样可以保证删除结点后的树仍为二叉平衡树。
            if (height(rootNode.left) > height(rootNode.right)) {//找前驱节点替换
                rootNode.data = findMax(rootNode.left).data;
                rootNode.left = removeAvlNode(rootNode.data, rootNode.left);
            } else {//后继节点替换
                //右子树的最小值替换当前节点值
                rootNode.data = findMin(rootNode.right).data;
                //移除用于替换的点
                rootNode.right = removeAvlNode(rootNode.data, rootNode.right);
            }

        } else {//只有一个孩子节点或者叶子节点的情况,次处返回的节点返回给上次递归，用于父节点链接该节点
            rootNode = (rootNode.left != null) ? rootNode.left : rootNode.right;
        }
        return rootNode;
    }

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
        return mRoot == null;
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
    public int size(AVLNode<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }
        int leftSize = size(currentNode.left);
        int rightSize = size(currentNode.right);
        return leftSize + rightSize + 1;
    }


    @Override
    public int height() {
        return mRoot == null ? 0 : mRoot.height;
    }

    @Override
    public String preOrder() {
        return preOrder(mRoot);
    }

    /**
     * 前序遍历,先遍历节点,再遍历左子树，最后遍历右子树
     *
     * @return
     */
    public String preOrder(AVLNode<T> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.data + ", ");
            sb.append(preOrder(root.left));
            sb.append(preOrder(root.right));
        }
        return sb.toString();
    }

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
    public String inOrder(AVLNode<T> root) {
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
    public String postOrder(AVLNode<T> root) {
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
    public String levelOrder(AVLNode<T> node) {
        StringBuilder sb = new StringBuilder();
        Queue<AVLNode<T>> queue = new Queue<>();
        while (node != null) {
            sb.append(node.data + ", ");
            AVLNode<T> leftNode = node.left;
            AVLNode<T> rightNode = node.right;
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


    @Override
    public T findMin() {
        return findMin(mRoot).data;
    }

    @Override
    public T findMax() {
        return findMax(mRoot).data;
    }

    @Override
    public BinaryNode findNode(T data) {
        //@see SearchTree中的实现
        //return findNode(T data, mRoot)
        return null;
    }

    /**
     * 递归查找元素
     *
     * @param data
     * @param currentNode
     * @return
     */
    public AVLNode findNode(T data, AVLNode<T> currentNode) {
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

    /**
     * 递归实现包含
     *
     * @param data
     * @param tree
     * @return
     */
    public boolean contains(T data, AVLNode<T> tree) {
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

    @Override
    public void clear() {
        mRoot = null;
    }
}
