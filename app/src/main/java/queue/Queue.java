package queue;

import List.Node;

/**
 * Created by chenming on 16/12/29.
 * 队列的实现也可以由只对收尾操作的LinkedList实现，这里给出它的基本实现原理
 */

public class Queue<T> {
    private Node<T> mHead;
    private Node<T> mTail;
    private int mSize;

    public Queue(){
        clear();
    }

    /**
     * 入队操作
     *
     * @param item
     */
    public void enquene(T item) {
        Node<T> node = new Node<>(item, null, null);
        if (isEmpty()) {
            //队列为空,从head插入
            mHead = node;
        } else {
            //队列不为空,从尾部插入
            mTail.next = node;
        }

        mTail = node;//尾部指针后移
        mSize++;
    }

    /**
     * 出队
     *
     * @return
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T item = mHead.mData;
        mHead = mHead.next;
        if (mHead == null) {
            mTail = null;//最后一个元素出队, 更新tail指向null
        }
        mSize--;
        return item;

    }

    /**
     * 获取队列尾部元素,不删除
     *
     * @return
     */
    public T tail() {
        return isEmpty() ? null : mTail.mData;
    }

    /**
     * 获取队列头部元素,不删除
     *
     * @return
     */
    public T head() {
        return isEmpty() ? null : mHead.mData;
    }

    public T getItem(int index) {
        if (index < 0 || index >= mSize) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = mHead;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.mData;
    }

    /**
     * 清空
     */
    public void clear() {
        mHead = mTail = null;
        mSize = 0;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return mHead == null && mTail == null;
    }

    public int size(){
        return mSize;
    }
}
