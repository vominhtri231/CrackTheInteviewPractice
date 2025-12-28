package tri.vo.cracktheinteview.arraystring;

import java.util.Arrays;

public class Urlify {

    void urlify(char[] input, int len) {
        int j = input.length - 1;
        int i = len - 1;
        while (i < j) {
            char cur = input[i];
            if (cur != ' ') {
                input[j] = cur;
                j = j - 1;
            } else {
                input[j] = '0';
                input[j - 1] = '2';
                input[j - 2] = '%';
                j = j - 3;
            }
            i = i - 1;
        }

    }

    public static void main(String[] args) {
        char[] result = new char[]{'M', 'r', '%', '2', '0', 'J', 'o', 'h', 'n', '%', '2', '0', 'S', 'm', 'i', 't', 'h'};
        char[] input = new char[]{'M', 'r', ' ', 'J', 'o', 'h', 'n', ' ', 'S', 'm', 'i', 't', 'h', ' ', ' ', ' ', ' '};

        Urlify urlify = new Urlify();
        urlify.urlify(input, 13);
        if (!Arrays.equals(result, input)) {
            throw new AssertionError();
        }
    }
}
