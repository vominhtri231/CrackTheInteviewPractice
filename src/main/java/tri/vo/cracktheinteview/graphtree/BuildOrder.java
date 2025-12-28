package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.Pair;

import java.util.*;

public class BuildOrder {

    List<String> createBuildOrder(Set<String> projects, List<Pair<String, String>> dependencies) {
        projects = new HashSet<>(projects);
        dependencies = new ArrayList<>(dependencies);
        List<String> buildOrder = new ArrayList<String>();

        while (!projects.isEmpty()) {
            Set<String> noDepts = new HashSet<>(projects);
            for (Pair<String, String> dep : dependencies) {
                noDepts.remove(dep.second);
            }

            if (noDepts.isEmpty()) {
                throw new RuntimeException("Found circle for projects: " + projects);
            }

            buildOrder.addAll(noDepts);
            projects.removeAll(noDepts);
            dependencies.removeIf(it -> noDepts.contains(it.first));
        }

        return buildOrder;
    }

    public static void main(String[] args) {
        BuildOrder buildOrder = new BuildOrder();

        List<String> buildOrderResult = buildOrder.createBuildOrder(new HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f")), Arrays.asList(new Pair<>("a", "d"), new Pair<>("f", "b"), new Pair<>("b", "d"), new Pair<>("f", "a"), new Pair<>("d", "c")));

        System.out.println(buildOrderResult);
    }
}
