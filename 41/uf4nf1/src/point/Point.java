package point;

/**
 * Abstract data type corresponding to a 2D point
 * @author ProvenSoft
 */
public class Point {
    //attributes
    private double x;
    private double y;
    //constructors
    /**
     * default constructor
     */
    public Point() {
        x = 0.0;
        y = 0.0;
    }
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    //accessors
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    //methods
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Point{");
        sb.append("x=");
        sb.append(x);
        sb.append(", y=");
        sb.append(y);
        sb.append("}");
        return sb.toString();
    }
    
    public double distance(Point other) {
        return Math.hypot(x, y);
    }
}
