package tri.vo.cracktheinteview.arraystring;

import java.util.HashSet;
import java.util.Set;

public class IsPermutation {

    boolean isPermutation(String a, String b) {
        Set<Character> tokens = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            tokens.add(a.charAt(i));
        }
        for (int i = 0; i < b.length(); i++) {
            if (!tokens.contains(b.charAt(i))) {
                return false;
            }
            tokens.remove(b.charAt(i));
        }
        return tokens.isEmpty();
    }

    public static void main(String[] args) {
        IsPermutation isPermutation = new IsPermutation();

        if (!isPermutation.isPermutation("abcd", "dcab")) {
            throw new AssertionError();
        }

        if (isPermutation.isPermutation("abcd", "abecd")) {
            throw new AssertionError();
        }

        if (isPermutation.isPermutation("abcd", "dcabec")) {
            throw new AssertionError();
        }

        if (isPermutation.isPermutation("abcd", "dca")) {
            throw new AssertionError();
        }
    }
}
