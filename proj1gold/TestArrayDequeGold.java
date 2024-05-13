import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        // about 1000 random calls
        for (int i = 0; i <= 999; i += 1) {
            // only use addFirst(), addLast(), removeFirst(), removeLast()
            double numBetweenZeroAndOne = StdRandom.uniform();
            // 生成一个待插入的随机整数
            Random random = new Random();
            int randomInt = random.nextInt();
            // we have 4 operations, use random # to decide which operation
            if (numBetweenZeroAndOne < 0.25) {
                sad.addFirst(randomInt);
                ads.addFirst(randomInt);
            }
            else if (numBetweenZeroAndOne >= 0.25 && numBetweenZeroAndOne < 0.5) {
                sad.addLast(randomInt);
                ads.addLast(randomInt);
            }
            else if (numBetweenZeroAndOne >= 0.5 && numBetweenZeroAndOne < 0.75) {
                if (!sad.isEmpty()) {
                    Integer x = sad.removeFirst();
                    Integer y = ads.removeFirst();
                    assertEquals(x, y);
                }
                else {
                    continue;
                }
            }
            else {
                if (!sad.isEmpty()) {
                    Integer x = sad.removeLast();
                    Integer y = ads.removeLast();
                    assertEquals(x, y);
                }
                else {
                    continue;
                }
            }
            // how to compare two objects?
            //assertEquals(sad, ads);
        }
    }
}
