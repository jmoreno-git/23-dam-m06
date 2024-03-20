package point;

/**
 *
 * @author ProvenSoft
 */
public class Vector extends Point {

    public Vector(double x, double y) {
        super(x, y);
    }

    public Vector() {
    }

    public Vector(Vector other) {
        super(other);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vector(");
        sb.append(getX());
        sb.append(",").append(getY());
        sb.append(")");
        return sb.toString();
    }    
}
