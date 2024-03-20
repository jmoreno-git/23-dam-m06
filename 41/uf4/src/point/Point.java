package point;

/**
 *
 * @author ProvenSoft
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }
    
    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }    

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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        return Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Point(");
        sb.append(x);
        sb.append(",").append(y);
        sb.append(")");
        return sb.toString();
    }
    
    public double distanceTo(Point other) {
        return Math.hypot(other.x-this.x, other.y-this.y);
    }    
}
