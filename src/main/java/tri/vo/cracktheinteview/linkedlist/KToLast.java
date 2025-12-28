package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.Arrays;
import java.util.List;

public class KToLast {

    interface IKToLast {
        int kToLast(LinkedNode node, int k);
    }

    static class SimpleKtoLast implements IKToLast {
        @Override
        public int kToLast(LinkedNode head, int k) {
            int len = 0;
            LinkedNode cur = head;

            while (cur != null) {
                len++;
                cur = cur.next;
            }

            if (k >= len) {
                return -1;
            }
            int pos = len - 1 - k;
            cur = head;

            while (pos > 0) {
                pos--;
                cur = cur.next;
            }
            return cur.value;
        }
    }

    static class FastSlowKtoLast implements IKToLast {
        @Override
        public int kToLast(LinkedNode head, int k) {
            LinkedNode fast = head;
            LinkedNode slow = head;

            while (k > 0 && fast != null) {
                fast = fast.next;
                k--;
            }

            if (fast == null) {
                return -1;
            }

            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }

            return slow.value;
        }
    }

    public static void main(String[] args) {
        IKToLast kToLast = new SimpleKtoLast();
        test(kToLast, Arrays.asList(3, 4, 9, 10, 7, 8), 2, 10);
        test(kToLast, Arrays.asList(3, 4, 9, 10, 7, 8), 4, 4);
        test(kToLast, Arrays.asList(3, 4, 9, 10, 7, 8), 6, -1);
        test(kToLast, Arrays.asList(3, 4, 9, 10, 7, 8), 0, 8);
    }

    private static void test(KToLast.IKToLast kToLast, List<Integer> inputNode, int k, Integer expected) {
        LinkedNode node = LinkedNode.buildNode(inputNode);
        int kToLastValue = kToLast.kToLast(node, k);
        if (kToLastValue != expected) {
            throw new AssertionError();
        }
    }
}
