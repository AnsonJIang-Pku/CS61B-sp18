public class OffByN implements CharacterComparator {
    /** A class for off-by-N comparators. */
    private int N;
    public OffByN(int n) {
        N = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == N;
    }
}
