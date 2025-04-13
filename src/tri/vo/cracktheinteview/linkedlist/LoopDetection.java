package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoopDetection {

    LinkedNode detectLoop(LinkedNode head) {
        LinkedNode slow = head;
        LinkedNode fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            slow = slow.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null) {
            // no loop
            return null;
        }

        LinkedNode back = head;
        while (back != slow) {
            back = back.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LoopDetection ld = new LoopDetection();
        test(ld, Arrays.asList(2, 5, 10, 8, 10, 4, 4), Arrays.asList(3, 1, 2));
        test(ld, Arrays.asList(2, 5, 10), Arrays.asList(3, 1, 2, 8, 10, 3, 2, 10));
        test(ld, Collections.singletonList(2), Arrays.asList(3, 1, 2, 8, 10, 3, 2, 10));
        test(ld, Collections.emptyList(), Arrays.asList(3, 1, 2, 8, 10, 3, 2, 10));
        test(ld, Arrays.asList(2, 5, 10, 8, 10, 4, 4), Collections.singletonList(2));
        test(ld, Arrays.asList(2, 5, 10, 8, 10, 4, 4), Collections.emptyList());
        test(ld, Collections.singletonList(2), Collections.singletonList(2));
        test(ld, Collections.emptyList(), Collections.emptyList());
    }

    static void test(LoopDetection detection, List<Integer> list, List<Integer> circle) {
        LinkedNode circleNode = LinkedNode.buildNode(circle);
        if (circleNode != null) {
            LinkedNode lastInCircleNode = circleNode;
            while (lastInCircleNode.next != null) {
                lastInCircleNode = lastInCircleNode.next;
            }
            lastInCircleNode.next = circleNode;
        }

        LinkedNode listNode = LinkedNode.buildNode(list);
        if (listNode != null) {
            LinkedNode lastInListNode = listNode;
            while (lastInListNode.next != null) {
                lastInListNode = lastInListNode.next;
            }
            lastInListNode.next = circleNode;
        } else {
            listNode = circleNode;
        }

        LinkedNode expectedCircleNode = detection.detectLoop(listNode);
        if (expectedCircleNode != circleNode) {
            throw new AssertionError();
        }
    }


}
