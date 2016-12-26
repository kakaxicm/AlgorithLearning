package List;

/**
 * Created by chenming on 16/12/26.
 */

public class Node<T> {
    public Node<T> pre;
    public Node<T> next;
    public T mData;

    /**
     *
     * @param data 包裹数据
     * @param pre 前节点
     * @param next 后节点
     */
    public Node(T data, Node<T> pre, Node<T> next){
        mData = data;
        this.pre = pre;
        this.next = next;
    }
}
