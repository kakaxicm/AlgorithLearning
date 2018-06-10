package hash;

/**
 * Created by chenming on 2018/6/10
 */
public class HashMap<K, V> implements Map<K, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //默认大小16
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;//链表数组
    private float loadFactor;
    private int threhold;
    private int size;

    public HashMap(int cap, float loadFactor) {
        table = new Node[cap];
        this.loadFactor = loadFactor;
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
            if (size++ > threhold) {
                resize();//扩容
            }
            return;
        } else {
            Node p = first;

            while (p.next != null) {
                if (isNodesHit(key, p)) {//链表中存在hit的node
                    //替换value
                    p.setValue(value);
                    return;
                }
                p = p.next;
            }
            p.next = new Node(hash, key, value);


        }
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
     * TODO 扩容操作
     */
    private void resize() {

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
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
