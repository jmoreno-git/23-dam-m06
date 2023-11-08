
public class DummyMain {

    public static void main(String[] args) {
       DummyClass dc = new DummyClass();
       show(dc.dMethod());
       show(dc.sMethod());
       show(DummyInterface.sMethod());
       show(DummyInterface.X);
    }
    
    private static <T> void show(T s) {
        System.out.println(s);
    }
}
