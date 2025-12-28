package tri.vo.cracktheinteview.arraystring;

public class StringCompression {

    String stringCompress(String input) {
        StringBuilder output = new StringBuilder();
        int start = 0;

        while (start < input.length()) {
            char startChar = input.charAt(start);
            int end = start + 1;
            while (end < input.length() && input.charAt(end) == startChar) {
                end++;
            }
            output.append(startChar);
            output.append(end - start);
            start = end;
        }

        if (output.length() >= input.length()) {
            return input;
        } else {
            return output.toString();
        }
    }

    public static void main(String[] args) {
        StringCompression stringCompression = new StringCompression();
        if (!"a2b1c5a3".equals(stringCompression.stringCompress("aabcccccaaa"))) {
            throw new AssertionError();
        }

        if (!"abc".equals(stringCompression.stringCompress("abc"))) {
            throw new AssertionError();
        }

        if (!"aabb".equals(stringCompression.stringCompress("aabb"))) {
            throw new AssertionError();
        }
    }
}
