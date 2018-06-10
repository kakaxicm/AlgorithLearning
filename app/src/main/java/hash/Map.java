package hash;

/**
 * Created by chenming on 2018/6/10
 * 基本的map接口
 */
public interface Map<K, V> {
    /**
     * 大小
     * @return
     */
    int size();

    /**
     * 添加元素
     * @param key
     * @param object
     */
    void put(K key, V object);

    /**
     * 查找元素
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 删除元素
     * @param key
     * @return
     */
    V remove(K key);
}
