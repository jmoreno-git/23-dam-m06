package point;

/**
 *
 * @author ProvenSoft
 */
public class PointMain {

    public static void main(String[] args) {
        Point p1 = new Point(3.0, 2.0);
        System.out.println("p1: "+p1);
        Point p2 = new Point();
        Point p3 = new Point(p1);
        //
        Point[] points = new Point[3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        //
        for (Point point : points) {
            System.out.println(point);
        }
        //
        System.out.println("p1.equals(p3): "+p1.equals(p3));
        Point p4 = new Point(1.0, 2.0);
        for (Point point : points) {
            System.out.println(p4.distanceTo(point));
        }
        //
        Vector v1 = new Vector();
    }
    
}
