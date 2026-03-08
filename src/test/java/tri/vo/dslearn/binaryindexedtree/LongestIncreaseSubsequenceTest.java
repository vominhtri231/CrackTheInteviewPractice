package tri.vo.dslearn.binaryindexedtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestIncreasingSubsequenceTest {

    private final LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();

    @Test
    void testSimpleIncreasingSequence() {
        // [1, 2, 3, 4, 5] - entire array is LIS
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(5, lis.findLis(arr), "LIS of [1,2,3,4,5] should be 5");
    }

    @Test
    void testSimpleDecreasingSequence() {
        // [5, 4, 3, 2, 1] - each element is its own LIS
        int[] arr = {5, 4, 3, 2, 1};
        assertEquals(1, lis.findLis(arr), "LIS of [5,4,3,2,1] should be 1");
    }

    @Test
    void testSingleElement() {
        // [5] - single element
        int[] arr = {5};
        assertEquals(1, lis.findLis(arr), "LIS of single element should be 1");
    }

    @Test
    void testAllSameElements() {
        // [3, 3, 3, 3] - all same elements
        int[] arr = {3, 3, 3, 3};
        assertEquals(1, lis.findLis(arr), "LIS of all same elements should be 1");
    }

    @Test
    void testClassicExample() {
        // [10, 9, 2, 5, 3, 7, 101, 18]
        // LIS: [2, 3, 7, 101] or [2, 5, 7, 101] or [2, 3, 7, 18] etc.
        // Length = 4
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        assertEquals(4, lis.findLis(arr), "LIS of [10,9,2,5,3,7,101,18] should be 4");
    }

    @Test
    void testWithDuplicates() {
        // [1, 3, 2, 3, 4, 5]
        // LIS: [1, 2, 3, 4, 5] - length 5
        int[] arr = {1, 3, 2, 3, 4, 5};
        assertEquals(5, lis.findLis(arr), "LIS should handle duplicates correctly");
    }

    @Test
    void testAlternatingSequence() {
        // [1, 3, 2, 4, 3, 5]
        // LIS: [1, 2, 3, 5] or [1, 2, 4, 5] - length 4
        int[] arr = {1, 3, 2, 4, 3, 5};
        assertEquals(4, lis.findLis(arr), "LIS of alternating sequence should be 4");
    }

    @Test
    void testTwoElements() {
        // [1, 2] - increasing
        int[] arr1 = {1, 2};
        assertEquals(2, lis.findLis(arr1), "LIS of [1,2] should be 2");

        // [2, 1] - decreasing
        int[] arr2 = {2, 1};
        assertEquals(1, lis.findLis(arr2), "LIS of [2,1] should be 1");
    }

    @Test
    void testLargeRangeValues() {
        // Test with large value range (coordinate compression)
        // [100, 1, 50, 25, 75, 200]
        // LIS: [1, 25, 75, 200] - length 4
        int[] arr = {100, 1, 50, 25, 75, 200};
        assertEquals(4, lis.findLis(arr), "LIS should handle large value ranges with compression");
    }

    @Test
    void testNegativeNumbers() {
        // [-5, -2, -8, -1, 0, 3]
        // LIS: [-5, -2, -1, 0, 3] - length 5
        int[] arr = {-5, -2, -8, -1, 0, 3};
        assertEquals(5, lis.findLis(arr), "LIS should handle negative numbers");
    }

    @Test
    void testMixedPositiveNegative() {
        // [5, -3, 2, 8, -1, 4]
        // LIS: [-3, 2, 8] or [-3, 2, 4] - length 3
        int[] arr = {5, -3, 2, 8, -1, 4};
        assertEquals(3, lis.findLis(arr), "LIS should handle mixed positive and negative numbers");
    }

    @Test
    void testLongerSequence() {
        // [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
        // This is a classic LIS problem with answer 6
        // One possible LIS: [0, 2, 6, 9, 11, 15]
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        assertEquals(6, lis.findLis(arr), "LIS of longer sequence should be 6");
    }

    @Test
    void testMultipleLISPaths() {
        // [1, 2, 4, 3]
        // Two LIS paths: [1, 2, 4] and [1, 2, 3] both with length 3
        int[] arr = {1, 2, 4, 3};
        assertEquals(3, lis.findLis(arr), "LIS should find correct length even with multiple paths");
    }

    @Test
    void testPlateau() {
        // [1, 2, 3, 3, 3, 4, 5]
        // LIS: [1, 2, 3, 4, 5] - length 5
        int[] arr = {1, 2, 3, 3, 3, 4, 5};
        assertEquals(5, lis.findLis(arr), "LIS should handle plateau correctly");
    }

    @Test
    void testZeroValues() {
        // [0, 0, 1, 2]
        // LIS: [0, 1, 2] - length 3
        int[] arr = {0, 0, 1, 2};
        assertEquals(3, lis.findLis(arr), "LIS should handle zero values");
    }
}
