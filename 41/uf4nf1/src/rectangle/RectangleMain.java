package rectangle;

/**
 *
 * @author ProvenSoft
 */
public class RectangleMain {

    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(3.0, 2.0);
        System.out.println("rect1: "+rect1.toString());
        System.out.println("Perimeter rect1: "+rect1.perimeter());
        System.out.println("Area rect1: "+rect1.area());
        Rectangle rect2 = new Rectangle(2.0, 4.0);
        System.out.println("rect1.equals(rect2): "+rect1.equals(rect2));
        Rectangle rect3 = new Rectangle(3.0, 2.0);
        System.out.println("rect1.equals(rect3): "+rect1.equals(rect3));
    }
    
}
