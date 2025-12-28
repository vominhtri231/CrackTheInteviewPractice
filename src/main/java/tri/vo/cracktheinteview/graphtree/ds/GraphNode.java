package tri.vo.cracktheinteview.graphtree.ds;

import java.util.*;

public class GraphNode {
    public String name;
    public List<GraphNode> children;

    public GraphNode(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public static Map<String, GraphNode> buildGraph(List<String[]> edges) {
        Map<String, GraphNode> nodeMap = new HashMap<>();
        for (String[] edge : edges) {
            String start = edge[0];
            String end = edge[1];

            GraphNode startNode = nodeMap.computeIfAbsent(start, GraphNode::new);
            GraphNode endNode = nodeMap.computeIfAbsent(end, GraphNode::new);

            startNode.children.add(endNode);
        }
        return nodeMap;
    }

    public static String toDotFormat(Map<String, GraphNode> graph) {
        StringBuilder sb = new StringBuilder("digraph G {\n");
        Set<String> edgesPrinted = new HashSet<>();

        for (GraphNode node : graph.values()) {
            for (GraphNode child : node.children) {
                String edge = "    \"" + node.name + "\" -> \"" + child.name + "\";\n";
                if (edgesPrinted.add(edge)) {
                    sb.append(edge);
                }
            }
        }

        sb.append("}");
        return sb.toString();
    }
}




