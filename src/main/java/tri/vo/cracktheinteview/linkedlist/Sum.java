package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sum {

    LinkedNode sumBackward(LinkedNode first, LinkedNode second) {
        LinkedNode wrapResult = new LinkedNode(0, null);
        LinkedNode last = wrapResult;
        int carry = 0;

        while (first != null || second != null || carry > 0) {
            int firstValue = 0;
            if (first != null) {
                firstValue = first.value;
            }
            int secondValue = 0;
            if (second != null) {
                secondValue = second.value;
            }

            int tempSum = firstValue + secondValue + carry;
            int digit = tempSum % 10;
            carry = tempSum / 10;

            last.next = new LinkedNode(digit, null);
            last = last.next;

            if (first != null) {
                first = first.next;
            }
            if (second != null) {
                second = second.next;
            }
        }

        return wrapResult.next;
    }

    LinkedNode revert(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedNode wrap = new LinkedNode(0, head);
        LinkedNode cur = head.next;

        while (cur != null) {
            head.next = cur.next;

            LinkedNode afterWrap = wrap.next;
            wrap.next = cur;
            cur.next = afterWrap;

            cur = head.next;
        }

        return wrap.next;
    }

    LinkedNode sumForward(LinkedNode first, LinkedNode second) {
        LinkedNode revertedFirst = revert(first);
        LinkedNode revertedSecond = revert(second);
        LinkedNode resultBackward = sumBackward(revertedFirst, revertedSecond);
        return revert(resultBackward);
    }

    public static void main(String[] args) {
        Sum sum = new Sum();
        testSumBackward(sum);
        testRevert(sum);
        testSumForward(sum);
    }

    static void testSumBackward(Sum sum) {
        testSumBackward(sum, Arrays.asList(7, 1, 6), Arrays.asList(5, 9, 2), Arrays.asList(2, 1, 9));
        testSumBackward(sum, Arrays.asList(7, 1, 6), Arrays.asList(5, 9), Arrays.asList(2, 1, 7));
        testSumBackward(sum, Arrays.asList(7, 1), Arrays.asList(5, 9, 2), Arrays.asList(2, 1, 3));
        testSumBackward(sum, Arrays.asList(7, 1), Arrays.asList(5, 9, 9), Arrays.asList(2, 1, 0, 1));
        testSumBackward(sum, Arrays.asList(7, 1), Arrays.asList(5, 9, 9, 9), Arrays.asList(2, 1, 0, 0, 1));
    }

    static void testSumBackward(Sum sum, List<Integer> firstInput, List<Integer> secondInput, List<Integer> expected) {
        LinkedNode first = LinkedNode.buildNode(firstInput);
        LinkedNode second = LinkedNode.buildNode(secondInput);
        LinkedNode result = sum.sumBackward(first, second);
        List<Integer> resultList = LinkedNode.getList(result);
        if (!resultList.equals(expected)) {
            throw new AssertionError("Expected " + expected + ", got " + resultList);
        }
    }

    static void testRevert(Sum sum) {
        testRevert(sum, Arrays.asList(1, 2, 3, 5));
        testRevert(sum, Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1));
        testRevert(sum, Collections.singletonList(9));
        testRevert(sum, Collections.emptyList());
    }

    static void testRevert(Sum sum, List<Integer> input) {
        LinkedNode head = LinkedNode.buildNode(input);
        LinkedNode revertedHead = sum.revert(head);
        List<Integer> resultList = LinkedNode.getList(revertedHead);

        Collections.reverse(input);
        if (!resultList.equals(input)) {
            throw new AssertionError("Expected " + input + ", got " + resultList);
        }
    }

    static void testSumForward(Sum sum) {
        testSumForward(sum, Arrays.asList(6, 1, 7), Arrays.asList(2, 9, 5), Arrays.asList(9, 1, 2));
        testSumForward(sum, Arrays.asList(6, 1, 7), Arrays.asList(9, 5), Arrays.asList(7, 1, 2));
        testSumForward(sum, Arrays.asList(1, 7), Arrays.asList(2, 9, 5), Arrays.asList(3, 1, 2));
        testSumForward(sum, Arrays.asList(1, 7), Arrays.asList(9, 9, 5), Arrays.asList(1, 0, 1, 2));
        testSumForward(sum, Arrays.asList(1, 7), Arrays.asList(9, 9, 9, 5), Arrays.asList(1, 0, 0, 1, 2));
    }

    static void testSumForward(Sum sum, List<Integer> firstInput, List<Integer> secondInput, List<Integer> expected) {
        LinkedNode first = LinkedNode.buildNode(firstInput);
        LinkedNode second = LinkedNode.buildNode(secondInput);
        LinkedNode result = sum.sumForward(first, second);
        List<Integer> resultList = LinkedNode.getList(result);
        if (!resultList.equals(expected)) {
            throw new AssertionError("Expected " + expected + ", got " + resultList);
        }
    }
}
