import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapFrame extends JFrame {
    Set<double[]> roads = new HashSet<>();
    List<double[]> coloredRoads = new ArrayList<>();

    public MapFrame(Set<Edge> edges, List<Edge> coloredEdges) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super?
        for (Edge e: edges) {
            this.roads.add(convertEdge(e));
        }
        if (coloredEdges != null) {
            for (Edge e: coloredEdges) {
                coloredRoads.add(convertEdge(e));
            }
        }

        MapPanel map = new MapPanel();
        add(map);
        normalize();
    }

    public class MapPanel extends JPanel {
        public void paintComponent(Graphics g) {
            for (double[] ec : roads) {
                g.drawLine((int) ec[0], (int) ec[1], (int) ec[2], (int) ec[3]);
            }
            for (double[] ec : coloredRoads) {
                g.setColor(Color.GREEN);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
                g.drawLine((int) ec[0], (int) ec[1], (int) ec[2], (int) ec[3]);
            }
        }
    }

    private double[] convertEdge(Edge e) {
        double earthRadius = 6378137.0;
        double lat1 = e.getInter1().getLat();
        double long1 = e.getInter1().getLong();
        double lat2 = e.getInter2().getLat();
        double long2 = e.getInter2().getLong();
        
        double x1 = Math.toRadians(long1) * earthRadius;
        double x2 = Math.toRadians(long2) * earthRadius;
        double y1 = - Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat1) / 2)) * earthRadius;
        double y2 = - Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat2) / 2)) * earthRadius;
        
        // equirectangular coordinates
        double[] ec = new double[]{x1, y1, x2, y2};
        return ec;
    }

    private void normalize() {
        double xMax = - Double.POSITIVE_INFINITY;
        double xMin = Double.POSITIVE_INFINITY;
        double yMax = - Double.POSITIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;

        for (double[] ec: roads) {
            xMax = (ec[0] > xMax) ? ec[0] : xMax;
            xMin = (ec[0] < xMin) ? ec[0] : xMin;
            yMax = (ec[1] > yMax) ? ec[1] : yMax;
            yMin = (ec[1] < yMin) ? ec[1] : yMin;
            xMax = (ec[2] > xMax) ? ec[2] : xMax;
            xMin = (ec[2] < xMin) ? ec[2] : xMin;
            yMax = (ec[3] > yMax) ? ec[3] : yMax;
            yMin = (ec[3] < yMin) ? ec[3] : yMin;
        } 

        double reso = (yMax - yMin + 150) / (xMax - xMin + 150);
        double width = 1440;
        double height = (width * reso);
        if (height > 810) {
            height = 810;
            width = (int)(height / reso);
        }
        setSize((int)(width + 150), (int)(height + 150));

        double adjust = width / (xMax - xMin + 150);
        for (double[] arr : roads) {
            arr[0] = (arr[0] - xMin) * adjust + 60;
            arr[1] = (arr[1] - yMin) * adjust;
            arr[2] = (arr[2] - xMin) * adjust + 60;
            arr[3] = (arr[3] - yMin) * adjust;
        }
        for (double[] arr : coloredRoads) {
            arr[0] = (arr[0] - xMin) * adjust + 60;
            arr[1] = (arr[1] - yMin) * adjust;
            arr[2] = (arr[2] - xMin) * adjust + 60;
            arr[3] = (arr[3] - yMin) * adjust;
        }

    }

} 

