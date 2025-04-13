package tri.vo.cracktheinteview.stackqueue;

import java.util.*;

public class SortStack {

    <T extends Comparable<T>> Stack<T> sort(Stack<T> input) {
        Stack<T> result = new Stack<>();

        while (!input.isEmpty()) {
            T cur = input.pop();

            Stack<T> revertedResult = new Stack<>();
            while (!result.isEmpty()) {

                T curResult = result.pop();
                if (cur != null && cur.compareTo(curResult) < 0) {
                    revertedResult.push(cur);
                    cur = null;
                }

                revertedResult.push(curResult);
            }

            if (cur != null) {
                revertedResult.push(cur);
            }

            while (!revertedResult.isEmpty()) {
                result.push(revertedResult.pop());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SortStack stack = new SortStack();
        test(stack, Arrays.asList(1, 2, 3));
        test(stack, Arrays.asList(9, 2, 1, 0));
        test(stack, Arrays.asList(4, 2, 9, 5, 1));
        test(stack, Arrays.asList());
        test(stack, Arrays.asList(3));
    }

    static void test(SortStack sortStack, List<Integer> input) {
        Stack<Integer> inputStack = new Stack<>();
        inputStack.addAll(input);

        Stack<Integer> outputStack = sortStack.sort(inputStack);

        List<Integer> actual = new ArrayList<>();
        while (!outputStack.isEmpty()) {
            actual.add(outputStack.pop());
        }

        List<Integer> expected = new ArrayList<>(input);
        expected.sort(Comparator.naturalOrder());

        if (!actual.equals(expected)) {
            throw new AssertionError("Original: " + input + ", expected: " + expected + ", actual: " + actual);
        }
    }
}
