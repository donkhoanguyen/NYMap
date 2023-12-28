import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    public static List<Edge> findShortestPath(Graph g, String start, String end) {
        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Edge> path = new HashMap<>();

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> (int) (distance.get(a) - distance.get(b)));
        for (Node n: g.getNodes()) {
            visited.put(n, false);
            distance.put(n, Double.POSITIVE_INFINITY);
            path.put(n, null);
            if (n.getId().equals(start)) {
                distance.replace(n, 0.0);
                pq.add(n);
            }
        }

        while (pq.size() > 0) {
            Node cur = pq.poll();
            if (cur.getId().equals(end)) {
                List<Edge> shortestPath = new ArrayList<>();
                Edge e = path.get(cur);
                while (e != null) {
                    shortestPath.add(e);
                    Node prev = e.getNeighbor(cur);
                    e = path.get(prev);
                    cur = prev;
                }
                return shortestPath;
            }
            visited.replace(cur, true);
            for (Edge e: g.allEdgesTo(cur)) {
                Node next = e.getNeighbor(cur);
                if (!visited.get(next)) {
                    if (distance.get(next) > distance.get(cur) + e.distance()) {
                        distance.replace(next, distance.get(cur) + e.distance());
                        path.replace(next, e);
                        pq.add(next);
                    }
                }
            }
        }
        
        return null;
        
    }
}   
