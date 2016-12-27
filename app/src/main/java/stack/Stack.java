package stack;
import List.Node;

/**
 * Created by chenming on 16/12/27.
 */

public class Stack<T> {
    public String ID;
    public Node<T> mTop;//栈顶指针
    private int mSize;

    /**
     * 压栈操作
     *
     * @param item
     */
    public void push(T item) {
        if (item == null) {
            return;
        }
        Node<T> newNode = new Node<>(item, null, mTop);//新建节点,next指向当前的top
        mTop = newNode;//TOP指针上移
        mSize++;
    }

    /**
     * 弹栈操作
     *
     * @return
     */
    public T pop() {
        T item = null;
        if (!isEmpty()) {
            item = mTop.mData;
            mTop = mTop.next;
            mSize--;
        }
        return item;
    }

    /**
     * 大小
     *
     * @return
     */
    public int size() {
        return mSize;
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty() {
        return mTop == null;
    }

    /**
     * 清空
     */
    public void clear() {
        while (!isEmpty()) {
            pop();
        }
        mSize = 0;
    }

    /**
     * 读栈顶值
     * @return
     */
    public T peek(){
        T item = null;
        item = mTop.mData;
        return item;
    }

}
