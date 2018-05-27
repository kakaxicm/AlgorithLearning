package List.dlinkedlist;

/**
 * Created by chenming on 2018/5/26
 */
public class DLinkedList<T> implements IList<T> {

    private DNode<T> head; //不带数据的头结点
    private DNode<T> tail; //指向尾部的指针


    public DLinkedList() {
        //初始化头结点
        this.head = this.tail = new DNode<>();
    }

    /**
     * 指定位置添加元素
     *
     * @param index
     * @param item
     */
    @Override
    public void add(int index, T item) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (item == null) {
            return;
        }

        DNode<T> preNode = this.head;
        int j = 0;
        //查找要插入结点位置的前一个结点
        while (preNode.next != null && j < index) {
            j++;
            preNode = preNode.next;
        }
        //创建需要插入的结点,并让其前继指针指向front,后继指针指向front.next
        DNode<T> newNode = new DNode<>(item, preNode, preNode.next);
        //表中间插入preNode的下一个节点的前驱节点指向新节点
        if (preNode.next != null) {
            preNode.next.prev = newNode;
        }

        //preNode的后继指针指向新节点
        preNode.next = newNode;

        if (preNode == tail) {//尾部添加元素，更新尾部指针
            tail = newNode;
        }
    }

    /**
     * 尾部添加元素
     *
     * @param item
     * @return
     */
    @Override
    public boolean add(T item) {
        if (item == null) {
            return false;
        }

        //创建需要插入的结点,并让其前继指针指向tail,后继指针指向tail.next
        DNode<T> newNode = new DNode<>(item, tail, tail.next);
        //tail的后继指针指向新节点
        tail.next = newNode;
        //修改tail
        tail = newNode;

        return true;
    }

    /**
     * 删除元素
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        int size = size();

        if (index < 0 || index >= size || isEmpty()) {
            return null;
        }

        DNode<T> p = this.head.next;
        int j = 0;
        //找到要删除的位置,index = 0指向head.next，所以条件为j <= index
        while (p != null && j < index) {
            p = p.next;
            j++;
        }
        if (j >= size()) {//数组越界
            throw new IndexOutOfBoundsException();
        }
        //链表中间删除元素
        if (p.next != null) {
            p.next.prev = p.prev;
        }
        p.prev.next = p.next;
        //如果是尾节点则更改tail为p的前驱节点
        if (p == tail) {
            tail = p.prev;
        }
        return p.data;
    }

    /**
     * 删除所有item元素
     * @param item
     * @return
     */
    public boolean removeAll(T item) {
        boolean result = false;
        if (item == null || isEmpty()) {
            return result;
        }
        DNode<T> p = this.head.next;
        while (p != null) {
            if (item.equals(p.data)) {//删除操作
                if (p == tail) {//p为尾部节点.更新尾节点指向,删除p
                    tail = p.prev;
                    tail.next = null;
                    p.prev = null;
                } else {//删除中间节点
                    p.prev.next = p.next;
                    p.next.prev = p.prev;

                }
                result = true;
            }

            p = p.next;//继续查找
        }
        return result;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int j = 0;
        DNode<T> node = this.head.next;
        while (node != null && j < index) {
            node = node.next;
            j++;
        }
        if (j >= size()) {//数组越界
            throw new IndexOutOfBoundsException();
        }
        if (node != null) {
            return node.data;
        }
        return null;
    }

    /**
     * 设置元素
     *
     * @param index
     * @param item
     * @return
     */
    @Override
    public T set(int index, T item) {

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index >= size()) {//数组越界
            throw new IndexOutOfBoundsException();
        }

        T old = null;
        int j = 0;
        DNode<T> node = this.head.next;
        while (node != null && j < index) {
            node = node.next;
            j++;
        }

        if (node != null) {
            old = node.data;
            node.data = item;
            return old;
        }
        return null;
    }

    /**
     * 链表大小
     *
     * @return
     */
    @Override
    public int size() {
        int size = 0;
        DNode<T> node = head.next;//从head.next开始
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public void clear() {
        head = tail = new DNode<>();
    }

    @Override
    public boolean contains(T item) {
        if (item == null) {
            return false;
        }
        DNode<T> p = head.next;
        while (p != null) {
            if (item.equals(p.data)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    public void printItems() {
        for (int i = 0; i < size(); i++) {
            System.out.println("item:" + get(i));
        }
    }
}
