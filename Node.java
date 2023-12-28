public class Node{
    private String id;
    private double latitude;
    private double longtitude;

    public Node(String id, double latitude, double longtitude) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getId() {
        return this.id;
    }

    public double getLat() {
        return this.latitude;
    }

    public double getLong() {
        return this.longtitude;
    }
}