package hash;

/**
 * Created by chenming on 2018/6/10
 */
public class HashMap<K, V> implements Map<K, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //默认大小16
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;//链表数组
    private int threhold;
    private int size;

    public HashMap(int cap, float loadFactor) {
        table = new Node[cap];
        this.threhold = (int) (cap * loadFactor);
    }

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        int index = indexFromHash(hash, table.length);
        Node first = table[index];
        if (first == null) {//空坑直接占上
            first = new Node(hash, key, value);
            table[index] = first;
        } else {
            Node p = first;

            while (p.next != null) {
                if (isNodesHit(key, p)) {//链表中存在hit的node
                    //替换value,直接反馈
                    p.setValue(value);
                    return;
                }
                p = p.next;
            }
            p.next = new Node(hash, key, value);
        }
        if (++size > threhold) {
            resize();//扩容
        }
        return;
    }


    /**
     * 判断key是否命中
     *
     * @param key
     * @param n
     * @return
     */
    private boolean isNodesHit(K key, Node n) {
        int hash = hash(key);
        if (n.getHash() == hash) {
            if (key == n.getKey() || (key != null) && (key.equals(n.getKey()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 扩容操作
     */
    private void resize() {
        int oldCap = table.length;
        int newCap = oldCap << 1;
        threhold = (int) (newCap * DEFAULT_LOAD_FACTOR);
        Node<K, V>[] newTable = new Node[newCap];
        for (int i = 0; i < oldCap; i++) {
            Node<K, V> first = table[i];

            if (first != null) {
                int hash = first.getHash();
                if (first.next == null) {
                    newTable[indexFromHash(hash, newCap)] = first;
                } else {//拆分单链表成两个部分，一个挂载低索引，另外一个挂载高索引
                    Node<K, V> next;//保存旧链表的后继节点
                    //两个单链表的首尾指针
                    Node<K, V> loHead = null, loTail = null;
                    Node<K, V> hiHead = null, hiTail = null;
                    Node<K, V> p = first;
                    do {
                        next = p.next;
                        if ((oldCap & p.getHash()) == 0) {//低位索引
                            if (loTail == null) {
                                loHead = p;
                            } else {
                                loTail.next = p;
                            }
                            loTail = p;
                        } else {
                            if (hiTail == null) {
                                hiHead = p;
                            } else {
                                hiTail.next = p;
                            }
                            hiTail = p;
                        }
                    } while ((p = next) != null);

                    if (loHead != null) {
                        loTail.next = null;
                        newTable[i] = loHead;
                    }

                    if (hiHead != null) {
                        hiTail.next = null;
                        newTable[i + oldCap] = hiHead;
                    }
                }
            }
        }
        table = newTable;//替换旧数组
    }

    /**
     * 对key重新hash
     *
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * hash函数，hash到索引的映射
     *
     * @param hash
     * @param len
     * @return
     */
    private int indexFromHash(int hash, int len) {
        int index = (len - 1) & hash;
        return index;
    }

    @Override
    public V get(K key) {
        int index = indexFromHash(hash(key), table.length);
        Node<K, V> first = table[index];
        if (first == null) {
            return null;
        } else {
            Node<K, V> p = first;
            while (p != null) {
                if (isNodesHit(key, p)) {//如果命中则返回节点值
                    return p.getValue();
                }
                p = p.next;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = indexFromHash(hash(key), table.length);
        Node<K, V> first = table[index];
        V oldVal;
        if (first != null) {
            if (first.next == null) {
                oldVal = first.getValue();
                //直接删除
                table[index] = null;
            } else {
                if (isNodesHit(key, first)) {
                    //直接删除头结点
                    table[index] = first.next;
                    first = null;
                } else {
                    Node<K, V> pre = first;
                    Node<K, V> p = pre.next;
                    while (p != null) {
                        if (isNodesHit(key, p)) {//找到命中节点
                            oldVal = p.getValue();
                            pre.next = p.next;//删除操作
                            p.next = null;
                            break;
                        }
                        pre = p;
                        p = p.next;
                    }

                    if (p == null) {//没找到命中节点,直接返回
                        return null;
                    }
                }

            }
        }
        size--;
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
