package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.GraphNode;

import java.util.*;

public class RouteBetweenNodes {

    interface ConnectedChecker {
        boolean connected(GraphNode start, GraphNode end);
    }

    static class ConnectedCheckerImpl implements ConnectedChecker {
        public boolean connected(GraphNode start, GraphNode end) {
            Set<GraphNode> visited = new HashSet<>();
            Queue<GraphNode> queue = new LinkedList<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                GraphNode cur = queue.remove();
                visited.add(cur);

                if (visited.contains(end)) {
                    return true;
                }

                for (GraphNode child : cur.children) {
                    if (!visited.contains(child)) {
                        queue.add(child);
                    }
                }
            }

            return false;

        }
    }


    public static void main(String[] args) {
        ConnectedChecker connectedChecker = new ConnectedCheckerImpl();
        testConnected(connectedChecker);
        testNoConnected(connectedChecker);
    }

    static void testConnected(ConnectedChecker connectedChecker) {
        Map<String, GraphNode> connectedGraph = GraphNode.buildGraph(Arrays.asList(
                new String[]{"1", "2"},
                new String[]{"2", "3"},
                new String[]{"3", "4"},
                new String[]{"4", "5"},
                new String[]{"5", "6"},
                new String[]{"6", "7"},
                new String[]{"7", "8"},
                new String[]{"8", "9"},
                new String[]{"9", "10"},
                new String[]{"10", "11"},
                new String[]{"11", "12"},
                new String[]{"12", "13"},
                new String[]{"13", "14"},
                new String[]{"14", "15"},
                new String[]{"15", "16"},
                new String[]{"16", "17"},
                new String[]{"17", "18"},
                new String[]{"18", "19"},
                new String[]{"19", "20"},
                new String[]{"20", "21"},
                new String[]{"21", "22"},
                new String[]{"22", "23"},
                new String[]{"23", "24"},
                new String[]{"24", "25"},
                new String[]{"25", "26"},
                new String[]{"26", "27"},
                new String[]{"27", "28"},
                new String[]{"28", "29"},
                new String[]{"29", "30"},
                new String[]{"6", "12"},
                new String[]{"3", "14"},
                new String[]{"4", "2"},
                new String[]{"1", "17"},
                new String[]{"10", "30"},
                new String[]{"20", "8"},
                new String[]{"19", "16"},
                new String[]{"21", "9"},
                new String[]{"30", "15"},
                new String[]{"7", "24"},
                new String[]{"11", "26"},
                new String[]{"18", "22"},
                new String[]{"13", "25"},
                new String[]{"9", "4"},
                new String[]{"16", "30"},
                new String[]{"5", "23"},
                new String[]{"8", "19"},
                new String[]{"14", "11"},
                new String[]{"12", "7"},
                new String[]{"17", "20"},
                new String[]{"2", "18"},
                new String[]{"22", "6"},
                new String[]{"15", "10"},
                new String[]{"25", "13"},
                new String[]{"23", "21"},
                new String[]{"26", "1"},
                new String[]{"24", "5"},
                new String[]{"27", "29"},
                new String[]{"28", "3"},
                new String[]{"30", "2"}
        ));

        GraphNode start = connectedGraph.get("1");
        GraphNode end = connectedGraph.get("30");

        if (!connectedChecker.connected(start, end)) {
            throw new AssertionError("");
        }
    }

    static void testNoConnected(ConnectedChecker connectedChecker) {
        Map<String, GraphNode> unconnectedGraph = GraphNode.buildGraph(Arrays.asList(
                        new String[]{"1", "2"},
                        new String[]{"2", "3"},
                        new String[]{"3", "4"},
                        new String[]{"4", "5"},
                        new String[]{"5", "6"},
                        new String[]{"6", "7"},
                        new String[]{"7", "8"},
                        new String[]{"8", "9"},
                        new String[]{"9", "10"},
                        new String[]{"10", "11"},
                        new String[]{"11", "12"},
                        new String[]{"12", "13"},
                        new String[]{"13", "14"},
                        new String[]{"14", "15"},
                        new String[]{"16", "17"},
                        new String[]{"17", "18"},
                        new String[]{"18", "19"},
                        new String[]{"19", "20"},
                        new String[]{"20", "21"},
                        new String[]{"21", "22"},
                        new String[]{"22", "23"},
                        new String[]{"23", "24"},
                        new String[]{"24", "25"},
                        new String[]{"25", "26"},
                        new String[]{"26", "27"},
                        new String[]{"27", "28"},
                        new String[]{"28", "29"},
                        new String[]{"29", "30"},
                        new String[]{"3", "6"},
                        new String[]{"5", "9"},
                        new String[]{"2", "12"},
                        new String[]{"13", "11"},
                        new String[]{"1", "7"},
                        new String[]{"6", "14"},
                        new String[]{"4", "10"},
                        new String[]{"8", "13"},
                        new String[]{"9", "2"},
                        new String[]{"16", "20"},
                        new String[]{"18", "22"},
                        new String[]{"21", "25"},
                        new String[]{"23", "26"},
                        new String[]{"17", "19"},
                        new String[]{"19", "24"},
                        new String[]{"20", "27"},
                        new String[]{"24", "30"},
                        new String[]{"25", "28"},
                        new String[]{"27", "16"}
                )
        );

        GraphNode start = unconnectedGraph.get("1");
        GraphNode end = unconnectedGraph.get("30");

        if (connectedChecker.connected(start, end)) {
            throw new AssertionError("");
        }
    }
}
