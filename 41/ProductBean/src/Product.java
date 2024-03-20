
import java.beans.*;
import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private PropertyChangeSupport propertySupport;
    private VetoableChangeSupport vetoableSupport;

    private String code;
    private String description;
    private double costPrice;
    private double stock;
    private double minStock;

    public Product() {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
    }

    public Product(String code, String description, double costPrice,
            double stock, double minStock) {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
        this.code = code;
        this.description = description;
        this.costPrice = costPrice;
        this.stock = stock;
        this.minStock = minStock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws PropertyVetoException {
        String previous = getCode();
        vetoableSupport.fireVetoableChange("code", previous, code);
        this.code = code;
        propertySupport.firePropertyChange("code", previous, code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) throws PropertyVetoException {
        double previous = getCostPrice();
        vetoableSupport.fireVetoableChange("costPrice", previous, costPrice);
        this.costPrice = costPrice;
        propertySupport.firePropertyChange("costPrice", previous, costPrice);
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) throws PropertyVetoException {
        double previous = getStock();
        vetoableSupport.fireVetoableChange("stock", previous, stock);
        this.stock = stock;
        propertySupport.firePropertyChange("stock", previous, stock);
    }

    public double getMinStock() {
        return minStock;
    }

    public void setMinStock(double minStock) {
        this.minStock = minStock;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("code=").append(code);
        sb.append(", description=").append(description);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", stock=").append(stock);
        sb.append(", minStock=").append(minStock);
        sb.append('}');
        return sb.toString();
    }

}
