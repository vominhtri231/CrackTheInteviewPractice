package tri.vo.cracktheinteview.linkedlist.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkedNode {
    public int value;
    public LinkedNode next;

    public LinkedNode(int value, LinkedNode next) {
        this.value = value;
        this.next = next;
    }

    public static List<Integer> getList(LinkedNode node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();

        LinkedNode cur = node;
        while (cur != null) {
            list.add(cur.value);
            cur = cur.next;
        }

        return list;
    }

    public static LinkedNode buildNode(List<Integer> input) {
        LinkedNode cover = new LinkedNode(0, null);
        LinkedNode cur = cover;
        for (Integer value : input) {
            LinkedNode newNode = new LinkedNode(value, null);
            cur.next = newNode;
            cur = newNode;
        }
        return cover.next;
    }
}


