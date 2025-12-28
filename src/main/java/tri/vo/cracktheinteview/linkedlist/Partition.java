package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Partition {

    LinkedNode partition(LinkedNode head, int k) {
        LinkedNode wrap = new LinkedNode(0, head);

        LinkedNode lower = wrap;
        while (lower.next != null && lower.next.value < k) {
            lower = lower.next;
        }

        if (lower.next == null) {
            // all values less than k
            return wrap.next;
        }

        LinkedNode higher = lower.next;
        LinkedNode pre = higher;
        LinkedNode cur = pre.next;

        while (cur != null) {
            if (cur.value >= k) {
                pre = cur;
                cur = cur.next;
            } else {
                // move the cur to after lower
                pre.next = cur.next;

                lower.next = cur;
                cur.next = higher;
                lower = cur;

                cur = pre.next;
            }
        }

        return wrap.next;
    }

    public static void main(String[] args) {
        Partition partition = new Partition();
        test(partition, Arrays.asList(3, 5, 8, 5, 10, 2, 1), 5);
        test(partition, Arrays.asList(3, 4, 3, 1, -3, 2, 1), 5);
        test(partition, Arrays.asList(6, 7, 8, 9, 5, 10, 12), 5);
        test(partition, Arrays.asList(3, 2, 2, 1, 5, 10, 12), 5);
        test(partition, Arrays.asList(6, 7, 2, 2, 1, 5, 10, 12), 5);
        test(partition, Collections.singletonList(6), 5);
        test(partition, Collections.singletonList(7), 5);
        test(partition, Collections.emptyList(), 5);
    }

    static void test(Partition partition, List<Integer> input, int k) {
        LinkedNode head = LinkedNode.buildNode(input);
        LinkedNode result = partition.partition(head, k);
        List<Integer> resultList = LinkedNode.getList(result);
        System.out.println(resultList);

        if (resultList.size() != input.size()) {
            throw new AssertionError();
        }

        boolean over = false;
        for (Integer cur : resultList) {
            if (cur >= k && !over) {
                over = true;
                continue;
            }

            if (cur < k && over) {
                throw new AssertionError();
            }
        }

    }
}
