public class Edge {
    private String roadId;
    private Node inter1;
    private Node inter2;

    public Edge(String roadId, Node inter1, Node inter2) {
        this.roadId = roadId;
        this.inter1 = inter1;
        this.inter2 = inter2;
    }

    public String getRoadId() {
        return this.roadId;
    }

    public Node getInter1() {
        return this.inter1;
    }

    public Node getInter2() {
        return this.inter2;
    }

    public double distance() {
        double lat1Rad = Math.toRadians(inter1.getLat());
        double long1Rad = Math.toRadians(inter1.getLong());
        double lat2Rad = Math.toRadians(inter2.getLat());
        double long2Rad = Math.toRadians(inter2.getLong());

        double latDiff = lat2Rad - lat1Rad;
        double longDiff = long2Rad - long1Rad;

        // Haversine formula
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(longDiff / 2) * Math.sin(longDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance in miles
        double distance = 3958.8 * c; // 3958.8 is earth radius.
        return distance;
    }

    public Node getNeighbor(Node inter) {
        if (inter.getId() == inter1.getId()) return inter2;
        else if (inter.getId() == inter2.getId()) return inter1;
        else return null;
    }

    public boolean goTo(Node inter) {
        if (inter.getId() != inter1.getId() && inter.getId() != inter2.getId()) return false;
        else return true;
    }
}
