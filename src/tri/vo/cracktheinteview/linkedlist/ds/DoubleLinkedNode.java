package tri.vo.cracktheinteview.linkedlist.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoubleLinkedNode extends LinkedNode {
    public DoubleLinkedNode next;
    public DoubleLinkedNode prev;

    public DoubleLinkedNode(int value, DoubleLinkedNode next, DoubleLinkedNode prev) {
        super(value, next);
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public static List<Integer> getList(DoubleLinkedNode node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();

        DoubleLinkedNode cur = node;
        while (cur != null) {
            list.add(cur.value);
            cur = cur.next;
        }

        return list;
    }

    public static DoubleLinkedNode buildNode(List<Integer> input) {
        DoubleLinkedNode cover = new DoubleLinkedNode(0, null, null);
        DoubleLinkedNode cur = cover;
        for (Integer value : input) {
            DoubleLinkedNode newNode = new DoubleLinkedNode(value, null, cur);
            cur.next = newNode;
            cur = newNode;
        }

        DoubleLinkedNode result = cover.next;
        if (result != null) {
            result.prev = null;
        }
        return result;
    }

    public static void main(String[] args) {
        DoubleLinkedNode node = DoubleLinkedNode.buildNode(Arrays.asList(3, 2, 4, 10));

        List<Integer> list = DoubleLinkedNode.getList(node);
        DoubleLinkedNode last = node;
        while (last.next != null) {
            last = last.next;
        }
        List<Integer> reverted = new ArrayList<>();
        while (last != null) {
            reverted.add(last.value);
            last = last.prev;
        }
        Collections.reverse(reverted);
        if (!reverted.equals(list)) {
            throw new AssertionError("Expected list" + list + " but got " + reverted);
        }
    }
}
