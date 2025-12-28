package tri.vo.dslearn;

public class AddBinaryIndexed1Based {
    private final int[] bits;

    public AddBinaryIndexed1Based(int n) {
        bits = new int[n + 1];
    }

    public AddBinaryIndexed1Based(int[] input) {
        int n = input.length;
        bits = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(i, input[i]);
        }
    }

    public void add(int i, int val) {
        i = i + 1;
        while (i < bits.length) {
            bits[i] += val;
            i += i & (-i);
        }
    }

    public int calculate(int i) {
        i = i + 1;
        int sum = 0;
        while (i > 0) {
            sum += bits[i];
            i -= i & (-i);
        }
        return sum;
    }
}
