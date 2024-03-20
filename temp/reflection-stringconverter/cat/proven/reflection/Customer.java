
package cat.proven.reflection;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class Customer {
    private String nif;
    private String name;
    private String phone;

    public Customer(String nif, String name, String phone) {
        this.nif = nif;
        this.name = name;
        this.phone = phone;
    }

    public Customer() {
    }

    public Customer(String nif) {
        this.nif = nif;
    }
    
    public Customer(Customer other) {
        this.nif = other.nif;
        this.name = other.name;
        this.phone = other.phone;
    }    

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
        Class c = this.getClass();
        String className = c.getSimpleName();
        sb.append(className+"{");
        Field[] fields = c.getDeclaredFields();
        for (Field f: fields) {
            String fieldName = f.getName();
            Object fieldValue = null;
            try {
                fieldValue = f.get(this);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
            sb.append(String.format("[%s=%s]", fieldName, fieldValue.toString()));
        }
        sb.append("}");
        return sb.toString();
    }
    
    
}
