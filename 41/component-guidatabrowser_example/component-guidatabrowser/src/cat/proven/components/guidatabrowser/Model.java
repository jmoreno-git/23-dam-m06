package cat.proven.components.guidatabrowser;

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
 * @author Jose.
 * @param <T> the base type of data.
 * @param <I> the type of the unique field of objects.
 */
public class Model<T,I> implements Serializable {
    
    private final PropertyChangeSupport propSupport;
    private final VetoableChangeSupport vetoSupport;
    
    private List<T> data;
    private int selectedIndex;

    public Model() {
        data = new ArrayList<>();
        propSupport = new PropertyChangeSupport(this);
        vetoSupport = new VetoableChangeSupport(this);
    }  
    
//<editor-fold defaultstate="collapsed" desc="property-change">
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propSupport.removePropertyChangeListener(listener);
    }
    
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propSupport.addPropertyChangeListener(propertyName, listener);
    }
    
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propSupport.removePropertyChangeListener(propertyName, listener);
    }
    
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoSupport.addVetoableChangeListener(listener);
    }
    
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoSupport.removeVetoableChangeListener(listener);
    }
    
    public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
        vetoSupport.addVetoableChangeListener(propertyName, listener);
    }
    
    public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
        vetoSupport.removeVetoableChangeListener(propertyName, listener);
    }
//</editor-fold>
  
    
    protected List<T> getData() {
        return data;
    }

    protected void setData(List<T> data) throws PropertyVetoException {
        List<T> previous = this.data;
        vetoSupport.fireVetoableChange("data", previous, data);
        this.data = data;
        propSupport.firePropertyChange("data", previous, data);        
    }

    protected int getSelectedIndex() {
        return selectedIndex;
    }

    protected void setSelectedIndex(int selectedIndex) throws PropertyVetoException {
        int previous = this.selectedIndex;
        vetoSupport.fireVetoableChange("selectedIndex", previous, selectedIndex);
        this.selectedIndex = selectedIndex;
        propSupport.firePropertyChange("selectedIndex", previous, selectedIndex);
    }
    
    protected T getSelectedElement() {
        return data.get(selectedIndex);
    }
    
    protected boolean setSelectedElement(T element) {
        boolean result = false;
        int index = data.indexOf(element);
        if (index >= 0) {
            T previous = getSelectedElement();
            propSupport.fireIndexedPropertyChange("data", index, previous, element);
            result = replace(previous, element);
        }
        return result;
    }
    
    protected boolean addElement(T element) {
        return data.add(element);
    }
    
    protected boolean removeElement(T element) {
        return data.remove(element);
    }
    
    protected T find(T element) {
        T found = null;
        int index = data.indexOf(element);
        if (index >= 0) {
            found = data.get(index);
        }
        return found;
    }
    
    protected boolean replace(T currentElement, T newElement) {
        boolean result = false;
        int index = data.indexOf(currentElement);
        if (index >= 0) {
            data.set(index, newElement);
        } 
        return result;
    }

    protected boolean validIndex(int index) {
        return ((index>=0) && (index<data.size()));
    }
    
}
