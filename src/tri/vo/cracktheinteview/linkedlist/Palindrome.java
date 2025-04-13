package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.DoubleLinkedNode;
import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Palindrome {

    public interface IPalindrome {
        boolean isPalindrome(LinkedNode head);
    }

    static class RevertCheckPalindrome implements IPalindrome {
        @Override
        public boolean isPalindrome(LinkedNode head) {
            LinkedNode reverted = revert(head);
            return isEquals(reverted, head);
        }

        LinkedNode revert(LinkedNode head) {
            LinkedNode wrap = new LinkedNode(0, null);
            while (head != null) {
                wrap.next = new LinkedNode(head.value, wrap.next);
                head = head.next;
            }

            return wrap.next;
        }

        boolean isEquals(LinkedNode first, LinkedNode second) {
            while (first != null && second != null) {
                if (first.value != second.value) {
                    return false;
                }

                first = first.next;
                second = second.next;
            }
            return first == null && second == null;
        }
    }

    static class DoubleLinkedIsPalindrome {

        public boolean isPalindrome(DoubleLinkedNode head) {
            if (head == null) {
                return true;
            }

            DoubleLinkedNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }

            while (head != null) {

                if (head.value != tail.value) {
                    return false;
                }

                head = head.next;
                tail = tail.prev;
            }

            return true;
        }
    }

    static class RecursiveCheckPalindrome implements IPalindrome {

        @Override
        public boolean isPalindrome(LinkedNode head) {
            if (head == null || head.next == null) {
                return true;
            }

            LinkedNode beforeLast = head;
            LinkedNode last = head.next;

            while (last.next != null) {
                last = last.next;
                beforeLast = beforeLast.next;
            }

            if (last.value != head.value) {
                return false;
            }

            head = head.next;
            beforeLast.next = null;

            return isPalindrome(head);
        }
    }

    public static void main(String[] args) {
        IPalindrome palindrome = new RevertCheckPalindrome();
        test(palindrome, Arrays.asList(1, 2, 5, 5, 2, 1), true);
        test(palindrome, Arrays.asList(1, 9, 1), true);
        test(palindrome, Arrays.asList(9, 9), true);
        test(palindrome, Collections.singletonList(1), true);
        test(palindrome, Collections.emptyList(), true);
        test(palindrome, Arrays.asList(1, 9, 5), false);
        test(palindrome, Arrays.asList(1, 2, 5, 2, 0), false);
        test(palindrome, Arrays.asList(1, 2), false);
    }

    public static void test(IPalindrome palindrome, List<Integer> input, boolean expected) {
        LinkedNode node = LinkedNode.buildNode(input);

        if (palindrome.isPalindrome(node) != expected) {
            throw new AssertionError();
        }
    }
}
