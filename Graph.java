import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Set<Node> nodes;
    private Set<Edge> edges;
    private Map<Node, Set<Edge>> allEdges;

    public Graph(Set<Node> nodes, Set<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        allEdges = new HashMap<>();

        for (Node n: nodes) {
            allEdges.put(n, new HashSet<>());
        }

        for (Edge e: edges) {
            allEdges.get(e.getInter1()).add(e);
            allEdges.get(e.getInter2()).add(e);
        }
    }

    public Set<Node> getNodes() {
        return this.nodes;
    }

    public Set<Edge> getEdges() {
        return this.edges;
    }

    public Set<Edge> allEdgesTo(Node n) {
        return allEdges.get(n);
    }
}