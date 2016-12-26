package List;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Created by chenming on 16/12/26.
 */

public class LinkedList<T> implements List<T> {

    private int mSize;
    private int mModifyCount;//修改变量,做同步异常判断
    //头尾标记节点
    private Node<T> mHeader;
    private Node<T> mTail;

    public LinkedList(){
        clear();
    }
    /**
     * 清空双向列表
     */
    @Override
    public void clear() {
        mHeader = new Node<T>(null, null, null);
        mTail = new Node<T>(null, mHeader, null);
        mHeader.next = mTail;
        mSize = 0;
        mModifyCount++;
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public T get(int index) {
        return getNode(index).mData;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> p = null;
        if (index < size() / 2) {
            p = mHeader.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = mTail;
            for (int i = size(); i > index; i--) {
                p = p.pre;
            }
        }
        return p;
    }

    @Override
    public T set(int index, T item) {
        Node<T> node = getNode(index);
        T oldItem = node.mData;
        node.mData = item;
        return oldItem;
    }

    @Override
    public boolean add(T item) {
        add(size(), item);
        return true;
    }

    @Override
    public void add(int index, T item) {
        addNodeBefore(getNode(index), item);
    }

    /**
     * 节点前插入
     * @param node
     * @param item
     */
    private void addNodeBefore(Node<T> node, T item) {
        Node<T> newNode = new Node<>(item, node.pre, node);
        node.pre.next = newNode;
        node.pre = newNode;
        mSize++;
        mModifyCount++;
    }

    @Override
    public T remove(int index) {
        return removeNode(getNode(index));
    }

    private T removeNode(Node<T> node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
        mSize--;
        mModifyCount++;
        return node.mData;
    }

    @Override
    public boolean isEmpty() {
        return mSize == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public boolean contains(T item) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            T obj = iterator.next();
            if(obj.equals(item)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T item) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            T obj = iterator.next();
            if(obj.equals(item)){
                iterator.remove();
            }
        }
        return false;
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> currentNode = mHeader.next;
        private int expectedModCount = mModifyCount;//避免并发操作异常
        private boolean okToRemove;//remove的操作必须在next之后

        @Override
        public boolean hasNext() {
            return currentNode != mTail;
        }

        @Override
        public T next() {
            if(expectedModCount != mModifyCount){
                throw new ConcurrentModificationException();
            }

            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T nextItem = currentNode.mData;
            currentNode = currentNode.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if(expectedModCount != mModifyCount){
                throw new ConcurrentModificationException();
            }
            if(!okToRemove){
                throw new IllegalStateException();
            }
            LinkedList.this.removeNode(currentNode.pre);
            okToRemove = false;
            expectedModCount++;
        }
    }
}
