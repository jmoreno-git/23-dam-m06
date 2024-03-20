
package friend;

/**
 *
 * @author ProvenSoft
 */
public class FriendMain {

    public static void main(String[] args) {
        Friend friend1 = new Friend("Peter", 20);
        Friend friend2 = new Friend("Laura", 23);
        System.out.println("friend1: "+friend1.toString());
        System.out.println("friend2: "+friend2.toString());
        friend1.setAge(-10);
        System.out.println("friend1: "+friend1.toString());
        Friend friend3 = new Friend("Peter", 20);
        System.out.println("friend1==friend3: "+(friend1==friend3));
        System.out.println("friend1.equals(friend2): "+friend1.equals(friend2));
        System.out.println("friend1.equals(friend3): "+friend1.equals(friend3));
    }
    
}
