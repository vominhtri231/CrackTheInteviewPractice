package tri.vo.cracktheinteview.arraystring;

public class OneAway {

    boolean oneAway(String a, String b) {
        int diff = a.length() - b.length();
        boolean isEquals = diff == 0;

        if (Math.abs(diff) > 1) {
            return false;
        }

        if (diff < 0) {
            String c = a;
            a = b;
            b = c;
        }

        if (b.isEmpty()) {
            return true;
        }
        int j = 0;
        boolean skip = false;
        for (int i = 0; i < b.length(); i++) {
            char curA = a.charAt(i);
            char curB = b.charAt(j);

            if (curA == curB) {
                j++;
                continue;
            }

            if (skip) {
                return false;
            }

            skip = true;
            if (isEquals) {
                j++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        OneAway oneAway = new OneAway();

        if (!oneAway.oneAway("a", "b")) {
            throw new AssertionError();
        }

        if (!oneAway.oneAway("a", "")) {
            throw new AssertionError();
        }

        if (!oneAway.oneAway("", "z")) {
            throw new AssertionError();
        }

        if (!oneAway.oneAway("", "")) {
            throw new AssertionError();
        }

        if (!oneAway.oneAway("pale", "ple")) {
            throw new AssertionError();
        }

        if (!oneAway.oneAway("pales", "pale")) {
            throw new AssertionError();
        }

        if (!oneAway.oneAway("pale", "bale")) {
            throw new AssertionError();
        }

        if (oneAway.oneAway("pale", "bake")) {
            throw new AssertionError();
        }
    }

}
