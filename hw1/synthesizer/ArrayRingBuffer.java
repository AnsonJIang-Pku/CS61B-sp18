package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity; //有歧义的时候用this.表明变量是该实例的成员变量
        rb = (T[]) new Object[capacity]; //new数组的特殊语法
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T temp = rb[first];
        first = (first + 1) % capacity;
        fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

    // implement the abstract method iterator() in the BoundedQueue interface.
    @Override
    public Iterator<T> iterator() {
        return new BQIterator();
    }
    //Nested class for BoundedQueue iterator.
    private class BQIterator implements Iterator<T> {
        private int ptr;
        public BQIterator() {
            ptr = first;
        }
        public boolean hasNext() {
            //需要考虑满的情况???
            return ptr != last; //此时满的，就不认为没有下一个
        }
        public T next() {
            T returnItem = rb[ptr];
            ptr = (ptr + 1) % capacity;
            return returnItem;
        }
    }
}
