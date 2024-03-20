package cat.proven.formbind;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/**
 *
 * @author Jose
 */
public class Person implements Serializable {

    public Person() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     * @throws java.beans.PropertyVetoException
     */
    public void setId(int id) throws PropertyVetoException {
        int oldId = this.id;
        vetoableChangeSupport.fireVetoableChange(PROP_ID, oldId, id);
        this.id = id;
        propertyChangeSupport.firePropertyChange(PROP_ID, oldId, id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @throws java.beans.PropertyVetoException
     */
    public void setName(String name) throws PropertyVetoException {
        java.lang.String oldName = this.name;
        vetoableChangeSupport.fireVetoableChange(PROP_NAME, oldName, name);
        this.name = name;
        propertyChangeSupport.firePropertyChange(PROP_NAME, oldName, name);
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     * @throws java.beans.PropertyVetoException
     */
    public void setAge(int age) throws PropertyVetoException {
        int oldAge = this.age;
        vetoableChangeSupport.fireVetoableChange(PROP_AGE, oldAge, age);
        this.age = age;
        propertyChangeSupport.firePropertyChange(PROP_AGE, oldAge, age);
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

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", age=" + age + '}';
    }
    
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    private final transient VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_AGE = "age";
    private int id;
    private String name;
    private int age;
    
}
