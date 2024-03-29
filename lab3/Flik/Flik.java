/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // integers between -128 ~ 127, address assigned to them are the same one; outside this range, not the same address
        // return a == b; // should use "int a" and "int b".
        return a.equals(b);
    }
}
