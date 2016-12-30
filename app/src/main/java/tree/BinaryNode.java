package tree;

/**
 * Created by chenming on 16/12/30.
 */

public class BinaryNode<T extends Comparable> {
    public BinaryNode<T> left;//左结点

    public BinaryNode<T> right;//右结点

    public int mDupCount;//重复的节点值放到一个节点,mDupCount=1表示重复次数为1,两个相同值

    public T data;

    public BinaryNode(T data,BinaryNode left,BinaryNode right){
        this.data=data;
        this.left=left;
        this.right=right;
    }

    public BinaryNode(T data){
        this(data,null,null);

    }

    /**
     * 判断是否为叶子结点
     * @return
     */
    public boolean isLeaf(){
        return this.left==null&&this.right==null;
    }

}
