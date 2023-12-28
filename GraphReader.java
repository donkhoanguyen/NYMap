import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class GraphReader {
    public static Graph read(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            List<String[]> edgesList = new ArrayList<>();
            Map<String, Node> nodeMap = new HashMap<>();

            Set<Node> nodes = new HashSet<>();
            Set<Edge> edges = new HashSet<>();

            while (( line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                if (st.nextToken().equals("i")) {
                    Node n = new Node(st.nextToken(), Double.parseDouble(st.nextToken()), 
                            Double.parseDouble(st.nextToken()));
                    nodeMap.put(n.getId(), n);
                    nodes.add(n);
                } else {
                    edgesList.add(new String[]{st.nextToken(), st.nextToken(), st.nextToken()});
                }
            }
            
            
            for (String[] s : edgesList) {
                Edge e = new Edge(s[0], nodeMap.get(s[1]), nodeMap.get(s[2]));
                edges.add(e);
            }

            return new Graph(nodes, edges);
        } catch (Exception noFile) {
            System.out.println("invalid edge.");
        }
        return null;
    }
}


