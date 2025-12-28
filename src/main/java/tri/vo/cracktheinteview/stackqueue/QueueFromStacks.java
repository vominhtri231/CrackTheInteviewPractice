package tri.vo.cracktheinteview.stackqueue;

import java.util.*;

public class QueueFromStacks<T> {
    private final Stack<T> addStack = new Stack<>();
    private final Stack<T> removeStack = new Stack<>();

    void add(T value) {
        addStack.push(value);
    }

    T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        moveStackIfNecessary();
        return removeStack.pop();
    }

    T peek() {
        if (isEmpty()) {
            return null;
        }
        moveStackIfNecessary();
        return removeStack.peek();
    }

    boolean isEmpty() {
        return addStack.isEmpty() && removeStack.isEmpty();
    }

    private void moveStackIfNecessary() {
        if (removeStack.isEmpty()) {
            while (!addStack.isEmpty()) {
                removeStack.push(addStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            QueueFromStacks<Integer> queueFromStacks = new QueueFromStacks<>();
            Queue<Integer> base = new LinkedList<>();
            test(queueFromStacks, base);
        }
    }

    enum Operation {
        ADD, REMOVE, PEEK, IS_EMPTY
    }

    static Operation randOp(Random rand) {
        Operation[] operations = Operation.values();
        return operations[rand.nextInt(operations.length)];
    }

    static void test(QueueFromStacks<Integer> myQueue, Queue<Integer> referenceQueue) {
        Random rand = new Random();
        int n = rand.nextInt(100);

        for (int i = 0; i < n; i++) {
            Operation op = randOp(rand);

            switch (op) {
                case ADD:
                    System.out.println("Execute add");

                    int value = rand.nextInt(1000);

                    Exception addEx1 = null;
                    Exception addEx2 = null;

                    try {
                        referenceQueue.add(value);
                    } catch (Exception e) {
                        addEx1 = e;
                    }

                    try {
                        myQueue.add(value);
                    } catch (Exception e) {
                        addEx2 = e;
                    }

                    if ((addEx1 != null || addEx2 != null)
                            && (addEx1 == null || addEx2 == null
                            || !addEx1.getClass().equals(addEx2.getClass()))) {
                        throw new AssertionError("Add: Expected exception " + addEx1 + " but got " + addEx2);
                    }
                    break;

                case REMOVE:
                    System.out.println("Execute remove");

                    Exception removeEx1 = null;
                    Integer removeVal1 = null;
                    try {
                        removeVal1 = referenceQueue.remove();
                    } catch (Exception e) {
                        removeEx1 = e;
                    }

                    Exception removeEx2 = null;
                    Integer removeVal2 = null;
                    try {
                        removeVal2 = myQueue.remove();
                    } catch (Exception e) {
                        removeEx2 = e;
                    }

                    if (!Objects.equals(removeVal1, removeVal2)) {
                        throw new AssertionError("Remove: Expected value " + removeVal1 + " but got " + removeVal2);
                    }

                    if ((removeEx1 != null || removeEx2 != null)
                            && (removeEx1 == null || removeEx2 == null
                            || !removeEx1.getClass().equals(removeEx2.getClass()))) {
                        throw new AssertionError("Remove: Expected exception " + removeEx1 + " but got " + removeEx2);
                    }
                    break;

                case PEEK:
                    System.out.println("Execute peek");

                    Exception peekEx1 = null;
                    Integer peekVal1 = null;
                    try {
                        peekVal1 = referenceQueue.peek();
                    } catch (Exception e) {
                        peekEx1 = e;
                    }

                    Exception peekEx2 = null;
                    Integer peekVal2 = null;
                    try {
                        peekVal2 = myQueue.peek();
                    } catch (Exception e) {
                        peekEx2 = e;
                    }

                    if (!Objects.equals(peekVal1, peekVal2)) {
                        throw new AssertionError("Peek: Expected value " + peekVal1 + " but got " + peekVal2);
                    }

                    if ((peekEx1 != null || peekEx2 != null)
                            && (peekEx1 == null || peekEx2 == null
                            || !peekEx1.getClass().equals(peekEx2.getClass()))) {
                        throw new AssertionError("Peek: Expected exception " + peekEx1 + " but got " + peekEx2);
                    }
                    break;

                case IS_EMPTY:
                    System.out.println("Execute isEmpty");

                    boolean expected = referenceQueue.isEmpty();
                    boolean actual = myQueue.isEmpty();

                    if (expected != actual) {
                        throw new AssertionError("IsEmpty: Expected " + expected + " but got " + actual);
                    }
                    break;
            }
        }

        System.out.println("All operations passed ✅");
    }
}
