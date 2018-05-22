package List.singlelist;

/**
 * Created by chenming on 2018/5/22
 */
public class SingleList<T> implements ISingleList<T> {

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
}
