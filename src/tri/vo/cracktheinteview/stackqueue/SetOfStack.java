package tri.vo.cracktheinteview.stackqueue;

import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class SetOfStack<T> {
    // master's size >= 1
    // the top stack will not empty if the entire stack is not empty
    private final Stack<Stack<T>> master;
    private final int max;

    SetOfStack(int max) {
        master = new Stack<>();
        master.push(new Stack<>());
        this.max = max;
    }

    T peek() {
        return master.peek().peek();
    }

    T pop() {
        T value = master.peek().pop();
        if (master.peek().isEmpty() && master.size() > 1) {
            master.pop();
        }
        return value;
    }

    void push(T value) {
        if (master.peek().size() > max) {
            master.push(new Stack<>());
        }
        master.peek().push(value);
    }

    boolean isEmpty() {
        return master.peek().isEmpty();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            SetOfStack<Integer> setOfStack = new SetOfStack<>(5);
            test(setOfStack, new Stack<>());
        }
    }

    enum Operation {
        PUSH, POP, PEEK, IS_EMPTY
    }

    static Operation randOp(Random rand) {
        Operation[] operations = Operation.values();
        int randomIndex = rand.nextInt(operations.length);
        return operations[randomIndex];
    }

    static void test(SetOfStack<Integer> testStack, Stack<Integer> stack) {
        Random rand = new Random();
        int n = rand.nextInt(100);

        for (int i = 0; i < n; i++) {
            Operation op = randOp(rand);
            switch (op) {
                case PEEK:
                    System.out.println("Execute peek");

                    Exception peekEx1 = null;
                    Integer peekValue1 = null;
                    try {
                        peekValue1 = stack.peek();
                    } catch (Exception e) {
                        peekEx1 = e;
                    }

                    Exception peekEx2 = null;
                    Integer peekValue2 = null;
                    try {
                        peekValue2 = testStack.peek();
                    } catch (Exception e) {
                        peekEx2 = e;
                    }

                    if (!Objects.equals(peekValue1, peekValue2)) {
                        throw new AssertionError("Peek: Expected value " + peekValue1 + " but got " + peekValue2);
                    }

                    if ((peekEx1 != null || peekEx2 != null)
                            && (peekEx1 == null || peekEx2 == null
                            || !peekEx1.getClass().equals(peekEx2.getClass()))) {
                        throw new AssertionError("Peek: Expected exception " + peekEx1 + " but got " + peekEx2);
                    }
                    break;

                case PUSH:
                    System.out.println("Execute push");
                    int value = rand.nextInt(1000);
                    stack.push(value);
                    testStack.push(value);
                    break;

                case POP:
                    System.out.println("Execute pop");

                    Exception popEx1 = null;
                    Integer popValue1 = null;
                    try {
                        popValue1 = stack.pop();
                    } catch (Exception e) {
                        popEx1 = e;
                    }

                    Exception popEx2 = null;
                    Integer popValue2 = null;
                    try {
                        popValue2 = testStack.pop();
                    } catch (Exception e) {
                        popEx2 = e;
                    }

                    if (!Objects.equals(popValue1, popValue2)) {
                        throw new AssertionError("Pop: Expected value " + popValue1 + " but got " + popValue2);
                    }

                    if ((popEx1 != null || popEx2 != null)
                            && (popEx1 == null || popEx2 == null
                            || !popEx1.getClass().equals(popEx2.getClass()))) {
                        throw new AssertionError("Pop: Expected exception " + popEx1 + " but got " + popEx2);
                    }
                    break;

                case IS_EMPTY:
                    System.out.println("Execute isEmpty");
                    boolean expected = stack.isEmpty();
                    boolean actual = testStack.isEmpty();
                    if (expected != actual) {
                        throw new AssertionError("IsEmpty: Expected " + expected + " but got " + actual);
                    }
                    break;
            }
        }

        System.out.println("All operations passed ✅");
    }


}
