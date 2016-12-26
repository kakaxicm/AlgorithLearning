package List;

/**
 * Created by chenming on 16/12/26.
 */

public interface List<T> extends Collection<T>{
    T get(int index);
    T set(int index, T item);
    void add(int index, T item);
    T remove(int index);
    Iterator<T> iterator();
}
