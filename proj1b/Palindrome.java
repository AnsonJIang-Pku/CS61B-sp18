public class Palindrome {
    /** A class for palindrome operations. */
    public Deque<Character> wordToDeque(String word) {
        // "Character" is the object version of "char".
        Deque<Character> dWords = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            dWords.addLast(word.charAt(i));
        }
        return dWords;
    }

    private boolean checkNonLetter(String word) {
        for (int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            if (c < '0' || ('9' < c && c < 'A') || ('Z' < c && c < 'a') || c > 'z') {
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome(String word) {
        // Check non-letters characters.
        if (!checkNonLetter(word)) {
            return false;
        }
        Deque<Character> original = new LinkedListDeque<>();
        original = wordToDeque(word);
        String reversed = "";
        for (int i = 0; i < word.length(); i += 1) {
            reversed += original.removeLast();
        }
        return word.equals(reversed);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (!checkNonLetter(word)) {
            return false;
        }
        Deque<Character> original = new LinkedListDeque<>();
        original = wordToDeque(word);
        for (int i = 0; i < word.length() / 2; i += 1) {
            if (!cc.equalChars(original.get(i), original.get(word.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
