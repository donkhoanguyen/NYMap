import java.util.List;


public class Streetmap {
    public static void main(String[] args) throws Exception {
        String filename = args[0];
        Graph g = GraphReader.read(filename);
        List<Edge> shortestPath = null;
        int i = 1;
        boolean show = false, directions = false;
        String start = new String();
        String end = new String();

        while (i < args.length) {
            if (args[i].equals("--show")) {
                show = true;
            }
            else if (args[i].equals("--directions") ||
                        args[i].equals("--direction") ) {
                directions = true;
                 start = args[i + 1];
                 end = args[i + 2];
                i+=2;
            }
            i += 1; 
        }

        // before showing the map make the coloring first
        if (directions) {
            shortestPath= Dijkstra.findShortestPath(g, start, end);
            Double shortestDistance = 0.0;
            if (shortestPath != null) {
                for (Edge e: shortestPath) {
                    shortestDistance = shortestDistance + e.distance();
                }
            }
            System.out.println(shortestPath == null ? "No possible path" : shortestDistance);
        }
        if (show) {
            MapFrame map = new MapFrame(g.getEdges(), shortestPath);
            map.setVisible(true); // this is a print function
        }
        
    }
}
