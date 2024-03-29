import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik() {
        assertTrue(!Flik.isSameNumber(1, 2));
        assertTrue(Flik.isSameNumber(3, 3));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
