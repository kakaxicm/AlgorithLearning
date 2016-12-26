package List;

/**
 * Created by chenming on 16/12/26.
 */

public interface Iterator<T> {
    boolean hasNext();
    T next();
    void remove();
}
