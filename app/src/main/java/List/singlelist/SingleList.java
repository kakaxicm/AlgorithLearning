package List.singlelist;

import android.util.Log;

/**
 * Created by chenming on 2018/5/22
 */
public class SingleList<T extends Comparable> implements ISingleList<T> {

    protected SNode<T> headNode; //带数据头结点

    public SingleList() {
    }

    public SingleList(SNode<T> headNode) {
        this.headNode = headNode;
    }

    /**
     * 是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return headNode == null;
    }

    /**
     * 获取链表长度
     *
     * @return
     */
    @Override
    public int size() {
        int len = 0;
        SNode<T> p = headNode;
        while (p != null) {
            len++;
            p = p.next;
        }
        return len;
    }

    /**
     * 查找元素
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int scanIndex = 0;//扫描索引
        SNode<T> p = headNode;
        while (p != null && scanIndex < index) {
            scanIndex++;
            p = p.next;
        }
        if (p != null) {
            return p.data;
        } else {//遍历到链表结尾表明index越界
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 设置元素，返回旧的元素
     *
     * @param index
     * @param item
     * @return
     */
    @Override
    public T set(int index, T item) {
        //先判断是否为空
        if (isEmpty()) {
            return null;
        }
        //index校验
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //游到指定index
        int scanIndex = 0;//扫描索引
        SNode<T> p = headNode;
        while (p != null && scanIndex < index) {
            scanIndex++;
            p = p.next;
        }
        if (p != null) {
            T oldData = p.data;
            p.data = item;//设置新值
            return oldData;
        } else {//遍历到链表结尾表明index越界
            throw new IndexOutOfBoundsException();
        }

    }

    @Override
    public void add(int index, T item) {
        /**
         *
         * 1.头部插入新节点
         * 2.中间插入新节点
         * 3.末尾插入新节点
         */
        if (item == null) {
            return;
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        //头部插入
        if (isEmpty() || index == 0) {
            SNode<T> newNode = new SNode<>(item);
            newNode.next = headNode;
            headNode = newNode;
        } else {
            //中部或者尾部插入
            SNode<T> prev = headNode;
            int scanIndex = 0;//扫描索引
            while (prev.next != null && scanIndex < index - 1) {
                scanIndex++;
                prev = prev.next;//游标前移
            }
            //遍历到链表结尾,由于追加可以追加到尾部，所以这里对index的限定为size大小
            if (prev.next == null && scanIndex < index - 1) {
                throw new IndexOutOfBoundsException();
            }
            //尾部和中间插入
            SNode<T> newNode = new SNode<>(item);
            newNode.next = prev.next;//新节点连接后节点
            prev.next = newNode;//前节点链接后节点
        }
    }

    /**
     * 删除头结点
     * 删除中间节点
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        T old = null;
        if (index == 0) {
            //删除头结点
            old = headNode.data;
            headNode = headNode.next;
            return old;
        } else {
            //中部或者尾部删除
            SNode<T> prev = headNode;
            int scanIndex = 0;//扫描索引
            //查找目标位置的前一个节点
            while (prev.next != null && scanIndex < index - 1) {
                scanIndex++;
                prev = prev.next;//游标前移
            }
            //遍历到链表尾部，scanIndex仍然小于目标索引,所以上限越界
            if (prev.next == null && scanIndex < index) {
                throw new IndexOutOfBoundsException();
            }
            //需要删除的节点
            SNode<T> targetNode = prev.next;
            if (targetNode != null) {
                old = targetNode.data;
                prev.next = targetNode.next;
                targetNode = null;
            }
        }
        return old;
    }

    @Override
    public void clear() {
        headNode = null;
    }

    @Override
    public boolean contains(T item) {
        if (item == null) {
            return false;
        }

        if (isEmpty()) {
            return false;
        }

        SNode<T> node = headNode;
        while (node != null) {
            T data = node.data;
            if (data.equals(item)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 尾部添加元素
     *
     * @param item
     * @return
     */
    @Override
    public boolean add(T item) {
        /**
         *
         * 1.头部插入新节点
         * 2.中间插入新节点
         * 3.末尾插入新节点
         */
        if (item == null) {
            return false;
        }

        //头部插入
        if (isEmpty()) {
            SNode<T> newNode = new SNode<>(item);
            newNode.next = headNode;
            headNode = newNode;
        } else {
            //中部或者尾部插入
            SNode<T> prev = headNode;
            while (prev.next != null) {
                prev = prev.next;//游标前移
            }
            //尾部追加新节点
            SNode<T> newNode = new SNode<>(item);
            prev.next = newNode;//连接新节点
        }
        return true;
    }

    /**
     * 单链表的逆序操作(从表头非递归)
     */
    public void reverseList() {
        //逆序操作只有在2个元素以上才有效
        if (isEmpty() || headNode.next == null) {
            return;
        }
        SNode<T> curNode = headNode;//当前要逆序操作的节点
        SNode<T> reversHead = null;//当前的逆序头节点
        while (curNode != null) {
            SNode<T> nextNode = curNode.next;//保存下个操作节点
            curNode.next = reversHead;//操作节点指向逆序列表头,做断裂操作
            reversHead = curNode;//逆序列表头更新,操作节点做连接操作
            curNode = nextNode;//下个节点
        }
        headNode = reversHead;
    }

    /**
     * 从表尾递归逆序链表
     */
    public void reversListRecursion() {
        //链表size必须大于2才做逆序
        if (isEmpty()) {
            return;
        }
        if (size() <= 1) {
            return;
        }
        headNode = reverseNodeRecursion(headNode);
    }

    /**
     * 递归逆序链表,
     *
     * @param p 当前需要逆序的操作节点
     * @return 逆序后的链表头节点
     */
    private SNode<T> reverseNodeRecursion(SNode<T> p) {
        if (p.next == null) {//递归结束条件，从链表尾开始逆序操作
            return p;//直接返回尾部节点当做逆序链表的起点
        } else {
            SNode<T> result = reverseNodeRecursion(p.next);
            SNode<T> temp = result;
            //找到当前逆序链表的结尾然后把p挂上去
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = p;
            p.next = null;//当前节点已经挂载，则断开与原链表的联系
            return result;
        }
    }

    /**
     * 查找中间节点,快节点扫描速度是慢节点两倍
     *
     * @return
     */
    public T searchMid() {
        if (isEmpty()) {
            return null;
        }
        SNode<T> slowNode = headNode;
        SNode<T> fastNode = headNode;
        while (fastNode != null && fastNode.next != null && fastNode.next.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return slowNode.data;
    }

    /**
     * 查找倒数第K个元素
     *
     * @param k
     * @return
     */
    public T searchBackwardsElement(int k) {
        if (isEmpty()) {
            return null;
        }
        if (k >= size()) {
            return null;
        }
        SNode<T> leftNode = headNode;
        SNode<T> rightNode = leftNode;
        for (int i = 0; i < k; i++) {
            rightNode = rightNode.next;
        }
        //右边的扫描节点到结尾
        while (rightNode != null && rightNode.next != null) {
            rightNode = rightNode.next;
            leftNode = leftNode.next;
        }

        return leftNode.data;
    }

    /**
     * 基本排序
     */
    public void sortList() {
        if (isEmpty()) {
            return;
        }
        SNode<T> curNode = headNode;
        while (curNode.next != null) {//大循环
            //小循环
            SNode<T> tempNode = curNode.next;
            while (tempNode != null) {
                if (curNode.data.compareTo(tempNode.data) > 0) {
                    T tempData = curNode.data;
                    curNode.data = tempNode.data;
                    tempNode.data = tempData;
                }
                tempNode = tempNode.next;
            }
            curNode = curNode.next;
        }
    }

    /**
     * 删除重复元素,类似排序，需要嵌套遍历
     */
    public void removeAllDuplicateItems() {
        SNode<T> curNode = headNode;//大循环
        while (curNode != null) {
            SNode<T> temp = curNode;
            while (temp.next != null) {//由于要做删除操作，所以需要它的前驱，这里操作节点为temp.next
                if (temp.next.data.equals(curNode.data)) {//如果相等则做删除操作
                    //删除操作
                    temp.next = temp.next.next;//删除temp.next节点
                } else {
                    temp = temp.next;//否则下个节点
                }
            }
            curNode = curNode.next;
        }
    }

    /**
     * 递归逆序打印
     */
    public void printListReversely() {
        printListReversely(headNode);
    }

    /**
     * 逆序打印元素
     * @param head
     */
    public void printListReversely(SNode<T> head) {
        if (head != null) {
            printListReversely(head.next);
            Log.e("SingleList", head.data.toString());
        }
    }
}
