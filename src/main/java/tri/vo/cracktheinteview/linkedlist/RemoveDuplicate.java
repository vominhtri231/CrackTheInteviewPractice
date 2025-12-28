package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.*;

public class RemoveDuplicate {

    interface IRemoveDuplicate {
        LinkedNode removeDuplicate(LinkedNode head);
    }

    static class WithMemoryRemoveDuplicate implements IRemoveDuplicate {
        public LinkedNode removeDuplicate(LinkedNode head) {
            Set<Integer> over = new HashSet<>();
            LinkedNode cover = new LinkedNode(0, head);
            LinkedNode cur = cover.next, pre = cover;

            while (cur != null) {
                if (over.contains(cur.value)) {
                    pre.next = cur.next;
                    cur = cur.next;
                } else {
                    over.add(cur.value);
                    pre = cur;
                    cur = cur.next;
                }
            }

            return cover.next;
        }
    }

    static class withoutMemoryRemoveDuplicate implements IRemoveDuplicate {

        @Override
        public LinkedNode removeDuplicate(LinkedNode head) {
            LinkedNode first = head;

            while (first != null) {
                LinkedNode pre = first;
                LinkedNode cur = first.next;
                while (cur != null) {
                    if (first.value == cur.value) {
                        pre.next = cur.next;
                        cur = cur.next;
                    } else {
                        pre = cur;
                        cur = cur.next;
                    }
                }

                first = first.next;
            }

            return head;
        }
    }

    public static void main(String[] args) {
        IRemoveDuplicate removeDuplicate = new withoutMemoryRemoveDuplicate();
        test(removeDuplicate, Arrays.asList(1, 2, 3, 1, 1, 4), Arrays.asList(1, 2, 3, 4));
        test(removeDuplicate, Arrays.asList(1, 2, 3, 1, 1), Arrays.asList(1, 2, 3));
        test(removeDuplicate, Arrays.asList(1, 1), Collections.singletonList(1));
        test(removeDuplicate, Collections.singletonList(1), Collections.singletonList(1));
        test(removeDuplicate, Collections.emptyList(), Collections.emptyList());
    }

    private static void test(IRemoveDuplicate removeDuplicate, List<Integer> input, List<Integer> expected) {
        LinkedNode node1 = LinkedNode.buildNode(input);
        LinkedNode updatedNode1 = removeDuplicate.removeDuplicate(node1);
        List<Integer> updatedNodeList1 = LinkedNode.getList(updatedNode1);
        if (!expected.equals(updatedNodeList1)) {
            throw new AssertionError();
        }
    }
}
