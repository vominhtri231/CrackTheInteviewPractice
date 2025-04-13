package tri.vo.cracktheinteview.arraystring;

import java.util.HashSet;
import java.util.Set;

public class IsUnique {

    boolean isUnique1(String input) {
        Set<Character> tokens = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (tokens.contains(c)) {
                return false;
            }
            tokens.add(c);
        }
        return true;
    }

    boolean isUnique2(String input) {
        for (int i = 0; i < input.length() - 1; i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsUnique isUnique = new IsUnique();
        if (!isUnique.isUnique1("tw1239e")) {
            throw new AssertionError();
        }

        if (isUnique.isUnique1("tw1239et")) {
            throw new AssertionError();
        }
    }
}
