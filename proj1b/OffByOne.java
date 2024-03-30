public class OffByOne implements CharacterComparator {
    /** A class for off-by-1 comparators. */
    @Override
    public boolean equalChars(char x, char y) {
        int delta = x - y;

        return Math.abs(delta) == 1;
    }
}
