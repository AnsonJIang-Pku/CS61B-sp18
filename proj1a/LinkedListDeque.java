public class LinkedListDeque<T> {
    /** Nested LNode class. */
    public class LNode{
        public LNode prev;
        public T item;
        public LNode next;
        /** To construct sentinel. */
        public LNode(LNode _prev, LNode _next){
            prev = _prev;
            next = _next;
            //Don't initialize item. Useless.
        }
        /** To construct non-sentinel nodes. */
        public LNode(LNode _prev, T x, LNode _next) {
            item = x;
            prev = _prev;
            next = _next;
        }
    }

    public LNode sentinel;
    public int size;

    /** To build a sentinel. */
    public LNode Sentinel(){
        LNode temp = new LNode(null, null);
        //To build a circular structure.
        temp.next = temp;
        temp.prev = temp;
        return temp;
    }
    /** Creates an empty linked list deque. */
    public LinkedListDeque(){
        sentinel = Sentinel();
        size = 0;
    }
    /** Convenient to create new instance. */
    public LinkedListDeque(T x){
        sentinel = Sentinel();
        sentinel.next = new LNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        LNode temp = new LNode(sentinel, item, sentinel.next);
        sentinel.next = temp;
        temp.next.prev = temp;
        size += 1;
    }
    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item){
        LNode temp = new LNode(sentinel.prev, item, sentinel);
        sentinel.prev = temp;
        temp.prev.next = temp;
        size += 1;
    }
    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque(){
        LNode pt = sentinel.next;
        while(pt != sentinel){
            System.out.print(pt.item + " "); //Cannot use ' '(single quote)
            pt = pt.next;
        }
    }
    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        LNode temp = sentinel.next;
        temp.next.prev = sentinel;
        sentinel.next = temp.next;
        size -= 1;
        return temp.item;
    }
    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast(){
        if(size == 0){
            return null;
        }
        LNode temp = sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp;
        size -= 1;
        return temp.item;
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index){
        if(index >= size){
            return null;
        }
        int i = 0;
        LNode pt = sentinel.next;
        while(i < index){
            pt = pt.next;
            i += 1;
        }
        return pt.item;
    }
    /** Same as get, but uses recursion. */
    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return helperGet(sentinel.next, index);
    }
    //helper method for getRecursive().
    private T helperGet(LNode pt, int index){
        if(index == 0){
            return pt.item;
        }
        return helperGet(pt.next, index - 1);
    }

}