package tri.vo.dslearn;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BinaryIndexedTreeTest {

    // --- Define the Group Operations for Prefix Sums (Addition) ---

    // The main operation: Addition (a + b)
    private final BinaryOperator<Integer> func = Integer::sum;

    // The identity element: 0
    private final Integer defaultValue = 0;

    // The inverse operation: Subtraction (a - b)
    // For calculateRange(right, left-1): result = calculate(right) - calculate(left-1)
    // For update(oldVal, newVal): delta = newVal - oldVal
    private final BinaryOperator<Integer> invertFunc = (a, b) -> a - b;

    private BinaryIndexedTree<Integer> bit;
    private final List<Integer> initialData = Arrays.asList(5, 1, 2, 7, 3, 4); // N=6 elements

    // --- Test Setup ---

    @BeforeEach
    void setUp() {
        // Initialize the BIT with the input array
        // Expected cumulative sums: [5, 6, 8, 15, 18, 22]
        bit = new BinaryIndexedTree<>(initialData, func, defaultValue, invertFunc);
    }

    // --- Test Cases ---

    @Test
    void testInitialPrefixSums() {
        // Test calculate(i) which returns the prefix sum from index 0 to i (inclusive)

        // Sum[0..0] = 5
        assertEquals(5, bit.calculate(0), "Prefix sum at index 0 should be 5.");

        // Sum[0..1] = 5 + 1 = 6
        assertEquals(6, bit.calculate(1), "Prefix sum at index 1 should be 6.");

        // Sum[0..3] = 5 + 1 + 2 + 7 = 15
        assertEquals(15, bit.calculate(3), "Prefix sum at index 3 should be 15.");

        // Sum[0..5] = 5 + 1 + 2 + 7 + 3 + 4 = 22
        assertEquals(22, bit.calculate(5), "Prefix sum at last index should be 22.");
    }

    @Test
    void testCalculateRange() {
        // Test calculateRange(left, right) which returns Sum[left..right]

        // Sum[1..4] = 1 + 2 + 7 + 3 = 13
        // Should be: calculate(4) - calculate(0) = 18 - 5 = 13
        assertEquals(13, bit.calculateRange(1, 4), "Range sum [1..4] should be 13.");

        // Sum[3..5] = 7 + 3 + 4 = 14
        // Should be: calculate(5) - calculate(2) = 22 - 8 = 14
        assertEquals(14, bit.calculateRange(3, 5), "Range sum [3..5] should be 14.");

        // Range starting at 0: Sum[0..2] = 5 + 1 + 2 = 8
        assertEquals(8, bit.calculateRange(0, 2), "Range sum [0..2] should be 8.");

        // Single element range: Sum[2..2] = 2
        assertEquals(2, bit.calculateRange(2, 2), "Range sum [2..2] should be 2.");
    }

    @Test
    void testUpdate() {
        // Original data: [5, 1, 2, 7, 3, 4] -> Sum[0..5] = 22

        // 1. Update index 2 from 2 to 10. Delta is +8.
        // New array: [5, 1, 10, 7, 3, 4]
        int indexToUpdate = 2;
        int oldVal = 2;
        int newVal = 10;

        // Note: Your update method should internally handle the delta calculation: add(index, newVal - oldVal)
        bit.update(indexToUpdate, oldVal, newVal);

        // Test if total sum is correct: 22 + 8 = 30
        assertEquals(30, bit.calculate(5), "Total sum after update should be 30.");

        // Test a prefix sum: Sum[0..3] = 5 + 1 + 10 + 7 = 23
        assertEquals(23, bit.calculate(3), "Prefix sum at index 3 after update should be 23.");

        // 2. Update index 5 from 4 to -1. Delta is -5.
        // New array: [5, 1, 10, 7, 3, -1]
        bit.update(5, 4, -1);

        // Test if total sum is correct: 30 - 5 = 25
        assertEquals(25, bit.calculate(5), "Total sum after second update should be 25.");

        // Test a range sum: Sum[3..5] = 7 + 3 + (-1) = 9
        assertEquals(9, bit.calculateRange(3, 5), "Range sum [3..5] after second update should be 9.");
    }

    @Test
    void testBoundaryCondition_FirstElement() {
        // Update the first element (index 0) from 5 to 0. Delta is -5.
        bit.update(0, 5, 0);

        // Check new prefix sum at index 0: 0
        assertEquals(0, bit.calculate(0));

        // Check new prefix sum at last index: 22 - 5 = 17
        assertEquals(17, bit.calculate(5));

        // Check range [0..1]: 0 + 1 = 1
        assertEquals(1, bit.calculateRange(0, 1));
    }
}