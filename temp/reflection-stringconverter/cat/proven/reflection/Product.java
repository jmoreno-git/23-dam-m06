package cat.proven.reflection;

import cat.proven.utils.StringConverter;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class Product {
    private int id;
    private String desc;
    private double price;
    private int stock;

    public Product(int id, String desc, double price, int stock) {
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }
    
    public Product(Product other) {
        this.id = other.id;
        this.desc = other.desc;
        this.price = other.price;
        this.stock = other.stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return StringConverter.obj2string(this);
//        StringBuilder sb = new StringBuilder();
//        Class c = this.getClass();
//        String className = c.getSimpleName();
//        sb.append(className+"{");
//        Field[] fields = c.getDeclaredFields();
//        for (Field f: fields) {
//            String fieldName = f.getName();
//            Object fieldValue = null;
//            try {
//                fieldValue = f.get(this);
//            } catch (IllegalArgumentException ex) {
//                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            sb.append(String.format("[%s=%s]", fieldName, fieldValue.toString()));
//        }
//        sb.append("}");
//        return sb.toString();
    }
    
    
    
}
