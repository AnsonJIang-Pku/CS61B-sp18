public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    /** With starting size 8.*/
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }
    //Methods.
    /** Resize method.*/
    private void resize(int capacity) {
        T[] temp = (T []) new Object[capacity];
        //System.arraycopy(items, (nextFirst + 1) % items.length, temp, 1, size);
        for (int i = (nextFirst + 1) % items.length, j = 1; j <= size; i++, j++) {
            temp[j] = items[i % items.length];
        }
        nextLast = size + 1;
        items = temp;
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % items.length;
        size += 1;
    }
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 1; i <= size; i++) {
            System.out.print(items[(nextFirst + i) % items.length] + " ");
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        size -= 1;
        return items[nextFirst];
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1) % items.length;
        size -= 1;
        return items[nextLast];
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

}
