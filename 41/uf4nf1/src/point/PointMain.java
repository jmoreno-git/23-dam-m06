package point;

/**
 *
 * @author ProvenSoft
 */
public class PointMain {

    public static void main(String[] args) {
        Point p1 = new Point();
        System.out.println("p1: "+p1.toString());
        System.out.println("p1.x: "+p1.getX());
        System.out.println("p1.y: "+p1.getY());
        //
        Point p2 = new Point(1.0, 2.0);
        System.out.println("p2: "+p2.toString());
        //
        p1.setX(-1.0);
        p1.setY(1.0);
        System.out.println("p1: "+p1.toString());
        //
        System.out.println("Distance from p1 to p2: "+p1.distance(p2));
    }
    
}
