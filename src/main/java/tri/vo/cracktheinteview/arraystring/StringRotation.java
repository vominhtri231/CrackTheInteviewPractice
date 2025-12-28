package tri.vo.cracktheinteview.arraystring;

public class StringRotation {

    boolean isRotation(String s1, String s2) {
        if (s2.length() != s1.length()) {
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s1);
        stringBuilder.append(s1);
        String doubleS1 = stringBuilder.toString();

        return isSubstring(doubleS1, s2);
    }

    private boolean isSubstring(String main, String part) {
        int mainLength = main.length();
        int partLength = part.length();

        if (mainLength < partLength) {
            return false;
        }

        for (int i = 0; i < mainLength - partLength; i++) {
            int j = 0;
            for (; j < partLength; j++) {
                if (main.charAt(i + j) != part.charAt(j)) {
                    break;
                }
            }
            if (j == partLength) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        StringRotation stringRotation = new StringRotation();

        if (!stringRotation.isRotation("abcd", "abcd")) {
            throw new AssertionError();
        }

        if (!stringRotation.isRotation("waterbottle", "erbottlewat")) {
            throw new AssertionError();
        }

        if (stringRotation.isRotation("abcd", "bcca")) {
            throw new AssertionError();
        }

        if (stringRotation.isRotation("waterbottle", "erwat")) {
            throw new AssertionError();
        }

    }
}
