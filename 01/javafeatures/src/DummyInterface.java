
/**
 * Interface to test static and default methods
 * @author ProvenSoft
 */
public interface DummyInterface {
    int X = 9;
    default String dMethod() {
        return "idefault".concat(String.valueOf(X));
    }
    static String sMethod() {
        return "istatic".concat(String.valueOf(X));
    }
}
