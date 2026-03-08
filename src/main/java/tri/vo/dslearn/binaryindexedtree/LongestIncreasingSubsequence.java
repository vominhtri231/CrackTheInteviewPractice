package tri.vo.dslearn.binaryindexedtree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LongestIncreasingSubsequence {

    /**
     * Finds the length of the longest strictly increasing subsequence.
     *
     * @param arr input array
     * @return length of the LIS
     */
    int findLis(int[] arr) {
        // Called f(x) = length of longest increasing subsequence that ends with value <= x
        // f(x) = max(f(0),...f(x-1)) + 1 if x is in the array, otherwise f(x) = max(f(0),...f(x-1))
        // We use coordinate compression to map actual values to indices [1,n]
        // Without any special data structure, we will have O(n^2) time complexity to calculate f(x) for all x

        // Using Binary Indexed Tree , will storing d(x) = max (f(1), f(2), ... f(x))
        // Although MAX is not a group operation (no inverse), BIT works here
        // because we only increase values (monotonic updates)
        // With BIT, we can calculate f(x) in O(log n) time, and update f(x) in O(log n) time, so total time complexity is O(n log n)

        MaxBinaryIndexTree maxBinaryIndexTree = new MaxBinaryIndexTree(arr);
        for (int val : arr) {
            int prevLis = maxBinaryIndexTree.calculatePrevMax(val);
            int newLis = prevLis + 1;
            maxBinaryIndexTree.update(val, newLis);
        }
        return maxBinaryIndexTree.calculateFinalMax();
    }

    private static class MaxBinaryIndexTree {
        int[] bits;
        int n;
        Map<Integer, Integer> valueToIndex;

        MaxBinaryIndexTree(int[] input) {
            n = input.length;
            bits = new int[n + 1];

            // Coordinate compression: map values to indices [1..n]
            valueToIndex = new HashMap<>();
            int[] sortedDistinct = Arrays.stream(input).distinct().sorted().toArray();
            int idx = 1;
            for (int val : sortedDistinct) {
                valueToIndex.put(val, idx++);
            }
        }

        void update(int val, int lisVal) {
            int i = getIndex(val);
            while (i <= n) {
                bits[i] = Math.max(bits[i], lisVal);
                i += i & (-i);
            }
        }

        int calculatePrevMax(int val) {
            int i = getIndex(val) - 1;
            return calculate(i);
        }

        int calculateFinalMax() {
            return calculate(n);
        }

        int calculate(int i) {
            int maxVal = 0;
            while (i > 0) {
                maxVal = Math.max(maxVal, bits[i]);
                i -= i & (-i);
            }
            return maxVal;
        }

        int getIndex(int value) {
            return valueToIndex.get(value);
        }
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(longestIncreasingSubsequence.findLis(arr)); // expected: 4 (2,3,7,101)
    }
}
