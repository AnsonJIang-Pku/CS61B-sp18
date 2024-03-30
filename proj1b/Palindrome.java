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

    public boolean isPalindrome(String word) {
        Deque<Character> original = new LinkedListDeque<>();
        original = wordToDeque(word);
        String reversed = "";
        for (int i = 0; i < word.length(); i += 1) {
            reversed += original.removeLast();
        }
        return word.equals(reversed);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
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
