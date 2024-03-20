//package cat.proven.scrollablelist;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose 
 * @param <T> 
 */
public class ScrollableList<T> implements Serializable {
    
    private List<T> data;
    private int position;
    
    private final transient PropertyChangeSupport propertyChangeSupport;
    private final transient VetoableChangeSupport vetoableChangeSupport;
    
    public static final String PROP_DATA = "data";
    public static final String PROP_POSITION = "position";
    
    public ScrollableList() {
        position = -1;
        data = new ArrayList<>();
        propertyChangeSupport = new PropertyChangeSupport(this);
        vetoableChangeSupport = new VetoableChangeSupport(this);
        //add VetoableChangeListener to veto invalid values for position.
        vetoableChangeSupport.addVetoableChangeListener(
            PROP_POSITION, 
            (PropertyChangeEvent evt) -> {
                String propertyName = evt.getPropertyName();
                if (propertyName.equals(PROP_POSITION)) {
                    int newValue = (int) evt.getNewValue();
                    if ((newValue<0)||(newValue>=data.size())) {
                        throw new PropertyVetoException(
                                String.format("Out of range: %d", newValue), 
                                evt);
                    }
                }
            }
        );
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) throws PropertyVetoException {
        List<T> oldValue = this.data;
        vetoableChangeSupport.fireVetoableChange(PROP_DATA, oldValue, data);
        this.data = data;
        propertyChangeSupport.firePropertyChange(PROP_DATA, oldValue, data);
        //if list contains data, set position to 0.
        if (this.data.size()>0) setPosition(0);
    }
    
    public T getData(int index) throws PropertyVetoException {
        vetoableChangeSupport.fireVetoableChange(PROP_POSITION, this.position, index);
        return data.get(index);
    }
    
    public void setData(int index, T datum) throws PropertyVetoException {
        vetoableChangeSupport.fireVetoableChange(PROP_POSITION, this.position, index);
        T oldValue = data.get(index);
        vetoableChangeSupport.fireVetoableChange(PROP_DATA, oldValue, datum);
        data.set(index, datum);
        propertyChangeSupport.fireIndexedPropertyChange("PROP_DATA", index, oldValue, datum);
    }    
    
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) throws PropertyVetoException {
        int oldValue = this.position;
        vetoableChangeSupport.fireVetoableChange(PROP_POSITION, oldValue, position);
        this.position = position;
        propertyChangeSupport.firePropertyChange(PROP_POSITION, oldValue, position);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
    
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(propertyName, listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(propertyName, listener);
    }
    
    //methods.
    
    public boolean addElement(T element) {
        T oldValue = null;
        boolean b = false;
        b = this.data.add(element);
        propertyChangeSupport.firePropertyChange(PROP_DATA, oldValue, element);
        return b;
    }
    
    public void moveCurrentRelative(int step) throws PropertyVetoException {
        int oldValue = this.position;
        setPosition(oldValue+step);
    }
    
    public void moveToFirst() throws PropertyVetoException {
        setPosition(0);
    }
    
    public void moveToLast() throws PropertyVetoException {
        setPosition(data.size()-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScrollableList{");
        String format = "[%s]"; 
        String markformat = "[[%s]]";
        for (int i=0; i<data.size(); i++) {
            if (position==i) sb.append(String.format(markformat, data.get(i)));
            else sb.append(String.format(format, data.get(i)));
        }
        sb.append("}");
        return sb.toString();
    }
    
    
    
}
