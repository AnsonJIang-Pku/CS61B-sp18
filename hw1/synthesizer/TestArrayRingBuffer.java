package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        assertTrue(arb.isEmpty()); // test .isEmpty() method
        arb.enqueue(1); // test .enqueue() method
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(3, arb.fillCount()); // test .fillCount() method
        assertEquals(1, (long) arb.peek()); // test .peek() method
        int temp = arb.dequeue();
        assertEquals(1, temp); // test .dequeue() method
        assertEquals(2, arb.fillCount());
        arb.enqueue(4);
        arb.enqueue(5);
        //test iterator: foreach loop 不满的情况
        for (int x: arb) {
            System.out.println(x);
        }
        arb.enqueue(6); // test .enqueue() method (ring)
        assertEquals(5, arb.fillCount());
        //test iterator: foreach loop 满的情况
        Iterator<Integer> seer = arb.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
