package tri.vo.cracktheinteview.stackqueue;

import java.util.Stack;

public class MinStack<T extends Comparable<T>> {

    private final Stack<T> internal = new Stack<>();

    public void push(T item) {
        internal.push(item);
    }

    public T pop() {
        return internal.pop();
    }

    public T peek() {
        return internal.peek();
    }

    public boolean empty() {
        return internal.empty();
    }

    T min() {
        Stack<T> save = new Stack<>();
        T min = null;

        while (!internal.empty()) {
            T cur = internal.pop();
            if (min == null || min.compareTo(cur) > 0) {
                min = cur;
            }
            save.push(cur);
        }

        while (!save.empty()) {
            internal.push(save.pop());
        }
        return min;
    }

    public static void main(String[] args) {
        MinStack<Integer> minStack = new MinStack<>();
        if (minStack.min() != null) {
            throw new AssertionError();
        }
        minStack.push(1);
        if (minStack.min() != 1) {
            throw new AssertionError();
        }

        minStack.push(2);
        minStack.push(-3);
        minStack.push(9);
        if (minStack.min() != -3) {
            throw new AssertionError();
        }
    }
}
