package cat.proven.components.databrowser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Facade class for DataBrowser component.
 * @author Jose.
 * @param <T> the base type of data.
 * @param <I> the type of the unique field of objects.
 */
public class DataBrowser<T, I> {

    private final PropertyChangeSupport propSupport;
    private final VetoableChangeSupport vetoSupport;
    
    private final Model<T,I> model;
    private final Controller<T,I> controller;
    private final View<T,I> view;
    
    public DataBrowser() {
        //
        model = new Model();
        controller = new Controller();
        view = new View();
        //
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);
        //
        propSupport = new PropertyChangeSupport(this);
        vetoSupport = new VetoableChangeSupport(this);
        //
        model.addPropertyChangeListener(controller);
        model.addVetoableChangeListener(controller);
//        model.addPropertyChangeListener("data", controller);
//        model.addPropertyChangeListener("selectedIndex", controller);
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
//</editor-fold>
    
    public List<T> getData() {
        return model.getData();
    }
    
    public void setData(List<T> data) {
        try {
            model.setSelectedIndex(0);
            model.setData(data);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        controller.start();
    }
    
//<editor-fold defaultstate="collapsed" desc="positioning-methods">
//    public void moveForward() {
//        try {
//            model.setSelectedIndex(model.getSelectedIndex()+1);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void moveBackward() {
//        try {
//            model.setSelectedIndex(model.getSelectedIndex()-1);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void moveStart() {
//        try {
//            model.setSelectedIndex(0);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void moveEnd() {
//        try {
//            model.setSelectedIndex(model.getData().size()-1);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//</editor-fold>
  
}
