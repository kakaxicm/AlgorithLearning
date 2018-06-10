package hash;

/**
 * Created by chenming on 2018/6/10
 */
public class Node<K, V> {
    private int hash;//桶中的hash值,与key不一样
    private K key;
    private V value;
    public Node next;

    public Node(int hash, K key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    public final String toString() {
        return key + "=" + value;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
