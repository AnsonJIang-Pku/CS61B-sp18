public class LinkedListDeque<T> implements Deque<T> {
    /** Nested LNode class. */
    private class LNode {
        private LNode prev;
        private T item;
        private LNode next;
        /** To construct sentinel. */
        public LNode(LNode pPrev, LNode nNext) {
            prev = pPrev;
            next = nNext;
            //Don't initialize item. Useless.
        }
        /** To construct non-sentinel nodes. */
        public LNode(LNode pPrev, T x, LNode nNext) {
            item = x;
            prev = pPrev;
            next = nNext;
        }
    }

    private LNode sentinel;
    private int size;

    /** To build a sentinel. */
    private LNode makeSentinel() {
        LNode temp = new LNode(null, null);
        //To build a circular structure.
        temp.next = temp;
        temp.prev = temp;
        return temp;
    }
    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = makeSentinel();
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        LNode temp = new LNode(sentinel, item, sentinel.next);
        sentinel.next = temp;
        temp.next.prev = temp;
        size += 1;
    }
    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        LNode temp = new LNode(sentinel.prev, item, sentinel);
        sentinel.prev = temp;
        temp.prev.next = temp;
        size += 1;
    }
    @Override
    public void printDeque() {
        LNode pt = sentinel.next;
        while (pt != sentinel) {
            System.out.print(pt.item + " "); //Cannot use ' '(single quote)
            pt = pt.next;
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        LNode temp = sentinel.next;
        temp.next.prev = sentinel;
        sentinel.next = temp.next;
        size -= 1;
        return temp.item;
    }
    /** Removes and returns the item at the back.  */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        LNode temp = sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp.prev;
        size -= 1;
        return temp.item;
    }
    /** Gets the item at the given index, where 0 is the front. */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        LNode pt = sentinel.next;
        while (i < index) {
            pt = pt.next;
            i += 1;
        }
        return pt.item;
    }

    /** Same as get(), but uses recursion. */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return helperGet(sentinel.next, index);
    }
    //helper method for getRecursive().
    private T helperGet(LNode pt, int index) {
        if (index == 0) {
            return pt.item;
        }
        return helperGet(pt.next, index - 1);
    }

}

