package tri.vo.dslearn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddBinaryIndexed1BasedTest {

    private AddBinaryIndexed1Based bit;
    private final int[] initialData = {5, 1, 2, 7, 3, 4}; // N=6 elements

    // --- Test Setup ---

    @BeforeEach
    void setUp() {
        // Initialize the BIT with the input array
        // Expected cumulative sums: [5, 6, 8, 15, 18, 22]
        bit = new AddBinaryIndexed1Based(initialData);
    }

    // --- Test Cases ---

    @Test
    void testInitialPrefixSums() {
        // Test calculate(i) which returns the prefix sum from index 0 to i (inclusive)

        // Sum[0..0] = 5
        assertEquals(5, bit.calculate(0), "Prefix sum at index 0 should be 5.");

        // Sum[0..1] = 5 + 1 = 6
        assertEquals(6, bit.calculate(1), "Prefix sum at index 1 should be 6.");

        // Sum[0..2] = 5 + 1 + 2 = 8
        assertEquals(8, bit.calculate(2), "Prefix sum at index 2 should be 8.");

        // Sum[0..3] = 5 + 1 + 2 + 7 = 15
        assertEquals(15, bit.calculate(3), "Prefix sum at index 3 should be 15.");

        // Sum[0..4] = 5 + 1 + 2 + 7 + 3 = 18
        assertEquals(18, bit.calculate(4), "Prefix sum at index 4 should be 18.");

        // Sum[0..5] = 5 + 1 + 2 + 7 + 3 + 4 = 22
        assertEquals(22, bit.calculate(5), "Prefix sum at last index should be 22.");
    }

    @Test
    void testAddOperation() {
        // Original data: [5, 1, 2, 7, 3, 4] -> Sum[0..5] = 22

        // 1. Add 8 to index 2. New value is 2 + 8 = 10.
        // New array: [5, 1, 10, 7, 3, 4]
        bit.add(2, 8);

        // Test if total sum is correct: 22 + 8 = 30
        assertEquals(30, bit.calculate(5), "Total sum after adding 8 at index 2 should be 30.");

        // Test a prefix sum: Sum[0..3] = 5 + 1 + 10 + 7 = 23
        assertEquals(23, bit.calculate(3), "Prefix sum at index 3 after add should be 23.");

        // Test a prefix sum before the modified index: Sum[0..1] = 5 + 1 = 6
        assertEquals(6, bit.calculate(1), "Prefix sum at index 1 should remain 6.");

        // 2. Add -5 to index 5. New value is 4 + (-5) = -1.
        // New array: [5, 1, 10, 7, 3, -1]
        bit.add(5, -5);

        // Test if total sum is correct: 30 - 5 = 25
        assertEquals(25, bit.calculate(5), "Total sum after adding -5 at index 5 should be 25.");

        // Test a prefix sum: Sum[0..4] = 5 + 1 + 10 + 7 + 3 = 26
        assertEquals(26, bit.calculate(4), "Prefix sum at index 4 after second add should be 26.");
    }

    @Test
    void testRangeSum() {
        // Test range sum calculation using calculate(right) - calculate(left-1)

        // Sum[1..4] = 1 + 2 + 7 + 3 = 13
        // Should be: calculate(4) - calculate(0) = 18 - 5 = 13
        assertEquals(13, bit.calculate(4) - bit.calculate(0), "Range sum [1..4] should be 13.");

        // Sum[3..5] = 7 + 3 + 4 = 14
        // Should be: calculate(5) - calculate(2) = 22 - 8 = 14
        assertEquals(14, bit.calculate(5) - bit.calculate(2), "Range sum [3..5] should be 14.");

        // Range starting at 0: Sum[0..2] = 5 + 1 + 2 = 8
        assertEquals(8, bit.calculate(2), "Range sum [0..2] should be 8.");
    }

    @Test
    void testBoundaryCondition_FirstElement() {
        // Add -5 to the first element (index 0), changing 5 to 0
        bit.add(0, -5);

        // Check new prefix sum at index 0: 0
        assertEquals(0, bit.calculate(0), "Prefix sum at index 0 after adding -5 should be 0.");

        // Check new prefix sum at last index: 22 - 5 = 17
        assertEquals(17, bit.calculate(5), "Total sum after modifying first element should be 17.");

        // Check prefix sum at index 1: 0 + 1 = 1
        assertEquals(1, bit.calculate(1), "Prefix sum at index 1 should be 1.");
    }

    @Test
    void testBoundaryCondition_LastElement() {
        // Add 6 to the last element (index 5), changing 4 to 10
        bit.add(5, 6);

        // Check new prefix sum at index 5: 22 + 6 = 28
        assertEquals(28, bit.calculate(5), "Total sum after adding 6 at last index should be 28.");

        // Check that earlier prefix sums are unaffected
        assertEquals(15, bit.calculate(3), "Prefix sum at index 3 should remain 15.");
    }

    @Test
    void testConstructorWithSize() {
        // Create a BIT with size 5 (all zeros initially)
        AddBinaryIndexed1Based emptyBit = new AddBinaryIndexed1Based(5);

        // All prefix sums should be 0
        assertEquals(0, emptyBit.calculate(0), "Prefix sum at index 0 should be 0 for empty BIT.");
        assertEquals(0, emptyBit.calculate(4), "Prefix sum at index 4 should be 0 for empty BIT.");

        // Add some values
        emptyBit.add(0, 10);
        emptyBit.add(2, 5);
        emptyBit.add(4, 3);

        // Test prefix sums
        assertEquals(10, emptyBit.calculate(0), "Prefix sum at index 0 should be 10.");
        assertEquals(15, emptyBit.calculate(2), "Prefix sum at index 2 should be 15.");
        assertEquals(18, emptyBit.calculate(4), "Prefix sum at index 4 should be 18.");
    }

    @Test
    void testMultipleAddsOnSameIndex() {
        // Add multiple values to the same index
        bit.add(3, 3); // 7 + 3 = 10
        bit.add(3, -5); // 10 - 5 = 5
        bit.add(3, 2); // 5 + 2 = 7

        // Net effect: 7 + 3 - 5 + 2 = 7 (no change)
        // Total sum should still be 22
        assertEquals(22, bit.calculate(5), "Total sum after multiple adds on same index should be 22.");

        // Prefix sum at index 3: 5 + 1 + 2 + 7 = 15
        assertEquals(15, bit.calculate(3), "Prefix sum at index 3 should be 15.");
    }

    @Test
    void testNegativeValues() {
        // Create a BIT with negative values
        int[] negativeData = {-5, 10, -3, 7, -2, 8};
        AddBinaryIndexed1Based negativeBit = new AddBinaryIndexed1Based(negativeData);

        // Sum[0..0] = -5
        assertEquals(-5, negativeBit.calculate(0), "Prefix sum at index 0 should be -5.");

        // Sum[0..1] = -5 + 10 = 5
        assertEquals(5, negativeBit.calculate(1), "Prefix sum at index 1 should be 5.");

        // Sum[0..5] = -5 + 10 - 3 + 7 - 2 + 8 = 15
        assertEquals(15, negativeBit.calculate(5), "Total sum should be 15.");

        // Add a negative value
        negativeBit.add(1, -15); // 10 - 15 = -5

        // New Sum[0..5] = 15 - 15 = 0
        assertEquals(0, negativeBit.calculate(5), "Total sum after adding -15 should be 0.");
    }

    @Test
    void testSingleElementArray() {
        // Create a BIT with a single element
        int[] singleElement = {42};
        AddBinaryIndexed1Based singleBit = new AddBinaryIndexed1Based(singleElement);

        // Prefix sum at index 0 should be 42
        assertEquals(42, singleBit.calculate(0), "Prefix sum at index 0 should be 42.");

        // Add 8 to the only element
        singleBit.add(0, 8);

        // New sum should be 50
        assertEquals(50, singleBit.calculate(0), "Prefix sum after adding 8 should be 50.");
    }
}

