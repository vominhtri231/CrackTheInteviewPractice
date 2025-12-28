package tri.vo.cracktheinteview.linkedlist;

import tri.vo.cracktheinteview.linkedlist.ds.LinkedNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Intersection {

    interface IntersectionSolver {
        LinkedNode findIntersection(LinkedNode first, LinkedNode second);
    }

    static class SimpleIntersectionSolver implements IntersectionSolver {

        @Override
        public LinkedNode findIntersection(LinkedNode first, LinkedNode second) {
            while (first != null) {
                LinkedNode tempSecond = second;
                while (tempSecond != null) {
                    if (first == tempSecond) {
                        return first;
                    }
                    tempSecond = tempSecond.next;
                }
                first = first.next;
            }
            return null;
        }
    }

    static class OptimizedIntersectionSolver implements IntersectionSolver {
        @Override
        public LinkedNode findIntersection(LinkedNode first, LinkedNode second) {
            int firstLen = len(first);
            int secondLen = len(second);
            if (firstLen > secondLen) {
                first = move(first, firstLen - secondLen);
            }
            if (secondLen > firstLen) {
                second = move(second, secondLen - firstLen);
            }

            while (first != null && second != null) {
                if (first == second) {
                    return first;
                }
                first = first.next;
                second = second.next;
            }
            return null;
        }

        private int len(LinkedNode head) {
            int count = 0;
            while (head != null) {
                head = head.next;
                count++;
            }
            return count;
        }

        private LinkedNode move(LinkedNode first, int len) {
            while (len > 0) {
                first = first.next;
                len--;
            }
            return first;
        }
    }

    public static void main(String[] args) {
        OptimizedIntersectionSolver solver = new OptimizedIntersectionSolver();
        test(solver, Arrays.asList(1, 2), Arrays.asList(9, 4, 5), Arrays.asList(3, 7, 9));
        test(solver, Arrays.asList(1, 2), Arrays.asList(9, 4, 5), Collections.emptyList());
        test(solver, Collections.emptyList(), Arrays.asList(9, 4, 5), Arrays.asList(3, 7, 9));
    }

    static void test(
            IntersectionSolver solver, List<Integer> firstList, List<Integer> secondList, List<Integer> intersectionList) {
        LinkedNode first = LinkedNode.buildNode(firstList);
        LinkedNode second = LinkedNode.buildNode(secondList);
        LinkedNode intersection = LinkedNode.buildNode(intersectionList);
        LinkedNode firstCombined = combine(first, intersection);
        LinkedNode secondCombined = combine(second, intersection);

        LinkedNode result = solver.findIntersection(firstCombined, secondCombined);
        if (result != intersection) {
            throw new AssertionError();
        }
    }

    static LinkedNode combine(LinkedNode first, LinkedNode second) {
        if (first == null) {
            return second;
        }

        LinkedNode firstLastNode = first;
        while (firstLastNode.next != null) {
            firstLastNode = firstLastNode.next;
        }

        firstLastNode.next = second;
        return first;
    }

}
