package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeleteMiddle {

    void deleteMiddle(LinkedNode head) {
        if (head == null || head.next == null) {
            return;
        }

        LinkedNode pre = head;
        LinkedNode cur = head.next;

        if (cur.next == null) {
            return;
        }

        pre.next = cur.next;
    }

    public static void main(String[] args) {
        DeleteMiddle deleteMiddle = new DeleteMiddle();
        test(deleteMiddle, Arrays.asList(1, 5, 6, 2, 9), Arrays.asList(1, 6, 2, 9));
        test(deleteMiddle, Arrays.asList(1, 5, 6), Arrays.asList(1, 6));
        test(deleteMiddle, Collections.singletonList(1), Collections.singletonList(1));
        test(deleteMiddle, Arrays.asList(1, 8), Arrays.asList(1, 8));
        test(deleteMiddle, Collections.emptyList(), Collections.emptyList());

    }

    static void test(DeleteMiddle deleteMiddle, List<Integer> input, List<Integer> expect) {
        LinkedNode head = LinkedNode.buildNode(input);
        deleteMiddle.deleteMiddle(head);

        List<Integer> afterDeletion = LinkedNode.getList(head);
        if (!afterDeletion.equals(expect)) {
            throw new AssertionError();
        }
    }
}
