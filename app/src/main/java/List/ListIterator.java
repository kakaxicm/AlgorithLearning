package List;

/**
 * Created by chenming on 16/12/26.
 */

public interface ListIterator<T> extends Iterator<T>{
    boolean hasPrevious();
    T previous();
}
