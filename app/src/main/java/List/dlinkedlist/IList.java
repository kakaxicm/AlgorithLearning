package List.dlinkedlist;

/**
 * Created by chenming on 2018/5/22
 */
public interface IList<T> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(T item);

    boolean add(T item);

    T get(int index);

    T set(int index, T item);

    void add(int index, T item);

    T remove(int index);
}
