import java.beans.*;
import java.io.Serializable;
/**
 * Class BeanA
 * @author Jose Moreno
 */
public class BeanA implements Serializable {
    
    public static final String X_PROPERTY = "x";
    private final PropertyChangeSupport propertySupport;
    
    private String x;
        
    public BeanA() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public String getX() {
        return x;
    }
    
    public void setX(String value) {
        String oldValue = x;
        x = value;
        propertySupport.firePropertyChange(X_PROPERTY, oldValue, x);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    @Override
    public String toString() {
        return "BeanA{" + "x=" + x + '}';
    }    
    
}
