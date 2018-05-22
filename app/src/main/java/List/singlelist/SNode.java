package List.singlelist;

/**
 * Created by chenming on 2018/5/22
 * 单链表节点
 */
public class SNode<T> {
    public T data;//数据域
    public SNode<T> next;//地址域

    public SNode(T data){
        this.data=data;
    }

    public SNode(T data,SNode<T> next){
        this.data=data;
        this.next=next;
    }
}
