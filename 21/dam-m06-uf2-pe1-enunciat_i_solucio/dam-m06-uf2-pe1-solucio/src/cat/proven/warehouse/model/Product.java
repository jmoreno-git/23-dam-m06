package cat.proven.warehouse.model;

import java.util.Objects;

/**
 * Data type for product
 * @author ProvenSoft
 */
public class Product {
    private long id;
    private String code;
    private String name;
    private double price;
    private Product container;

    public Product(long id, String code, String name, double price, Product container) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.container = container;
    }

    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    public Product(Product other) {
        this.id = other.id;
        this.code = other.code;
        this.name = other.name;
        this.price = other.price;
        this.container = other.container;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getContainer() {
        return container;
    }

    public void setContainer(Product container) {
        this.container = container;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.code);
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
        sb.append("id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        String sContainer = (container==null) ? "any" : String.valueOf(container.getId());
        sb.append(", containerId=").append(sContainer);
        sb.append('}');
        return sb.toString();
    }
    
}