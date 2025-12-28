package tri.vo.cracktheinteview.arraystring;

import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation {


    boolean palindromePermutation(String input) {
        Set<Character> memo = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);
            if (memo.contains(cur)) {
                memo.remove(cur);
            } else {
                memo.add(cur);
            }
        }

        return memo.size() < 2;
    }

    public static void main(String[] args) {
        PalindromePermutation p = new PalindromePermutation();

        if (p.palindromePermutation("abcd")) {
            throw new AssertionError();
        }

        if (!p.palindromePermutation("abbbb")) {
            throw new AssertionError();
        }

        if (!p.palindromePermutation("tacocat")) {
            throw new AssertionError();
        }
    }
}
