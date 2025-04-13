package tri.vo.cracktheinteview.stackqueue;

import java.util.Arrays;
import java.util.Stack;

public class EightQueen {

    static class Node {
        Node pre;
        int value;
        int index;

        Node(Node pre, int index, int value) {
            this.pre = pre;
            this.index = index;
            this.value = value;
        }

        int[] toMap() {
            int[] map = new int[8];
            Node cur = this;
            while (cur != null) {
                map[cur.index] = cur.value;
                cur = cur.pre;
            }
            return map;
        }
    }

    public void eightQueensIterative() {
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < 8; i++) {
            stack.push(new Node(null, 0, i));
        }

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            int[] map = cur.toMap();

            if (cur.index == 7) {
                System.out.println(Arrays.toString(map));
            }

            for (int i = 0; i < 8; i++) {
                if (isValid(map, cur.index + 1, i)) {
                    Node newNode = new Node(cur, cur.index + 1, i);
                    stack.push(newNode);
                }
            }
        }

    }

    public void eightQueens() {
        int[] map = new int[8];
        putQueen(map, 0);
    }

    private void putQueen(int[] map, int i) {
        if (i == 8) {
            System.out.println(Arrays.toString(map));
        }

        for (int j = 0; j < 8; j++) {

            if (isValid(map, i, j)) {

                map[i] = j;
                putQueen(map, i + 1);
            }
        }
    }

    private boolean isValid(int[] map, int i, int value) {
        for (int j = 0; j < i; j++) {
            if (map[j] == value) {
                return false;
            }
        }

        for (int j = 0; j < i; j++) {
            if (value - i == map[j] - j) {
                return false;
            }
        }

        for (int j = 0; j < i; j++) {
            if (value + i == map[j] + j) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.eightQueensIterative();
    }
}
