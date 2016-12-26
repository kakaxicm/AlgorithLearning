package List;

/**
 * Created by chenming on 16/12/26.
 */

public interface Collection<T> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(T item);
    boolean add(T item);
    boolean remove(T item);
}
