public class ArrayDequeTest {
    public static void myTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(12);
        ad1.addFirst(5);
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(7);
        ad1.addLast(18);
        ad1.addLast(10);
        ad1.addLast(99);
        ad1.addLast(100);
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();


    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        myTest();
    }
}
