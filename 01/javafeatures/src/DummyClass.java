
public class DummyClass implements DummyInterface {
    @Override
    public String dMethod() {
        return "cdefault".concat(String.valueOf(X));
    }
    public String sMethod() {
        return "cstatic".concat(String.valueOf(X));
    }
}
