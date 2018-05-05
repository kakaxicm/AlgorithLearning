package List;

import java.util.NoSuchElementException;

/**
 * Created by chenming on 16/12/26.
 */

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 2;
    private int mSize;
    private T[] mItems;

    public ArrayList() {
        clear();
    }

    @Override
    public void clear() {
        mSize = 0;
        ensureCapacity(DEFAULT_SIZE);
    }

    /**
     * 扩容操作
     *
     * @param newCapacity 目标容量
     */
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < mSize) {
            return;
        }

        T[] oldItems = mItems;
        mItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            mItems[i] = oldItems[i];
        }
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return mItems[index];
    }

    @Override
    public T set(int index, T item) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldItem = mItems[index];
        mItems[index] = item;
        return oldItem;
    }

    @Override
    public boolean add(T item) {
        add(size(), item);
        return true;
    }

    @Override
    public void add(int index, T item) {
        //边界条件判断
        if (item == null) {
            return;
        }
        //插入下标的容错判断,插入在最前面
        if (index < 0) {
            index = 0;
        }

        //插入下标的容错判断,插入在最后面
        if (index > size()) {
            index = size();
        }

        if (mItems.length == size()) {//当前容量已达上限,扩容
            ensureCapacity(size() * 2);
        }

        //index后的元素后移
        for (int i = mSize; i > index; i--) {
            mItems[i] = mItems[i - 1];
        }
        //目标位置设置值
        mItems[index] = item;
        mSize++;//size++
    }

    @Override
    public T remove(int index) {
        //边界条件判断
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T toRemoveItem = mItems[index];
        //删除位置后面的元素前移
        for (int i = index; i < size() - 1; i++) {
            mItems[i] = mItems[i + 1];
        }
        mSize--;
        return toRemoveItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public boolean contains(T item) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (obj.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T item) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (obj.equals(item)) {
                iterator.remove();
            }
        }
        return false;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int mCurrentPos;

        @Override
        public boolean hasNext() {
            return mCurrentPos < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return mItems[mCurrentPos++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove(--mCurrentPos);
        }
    }


}
