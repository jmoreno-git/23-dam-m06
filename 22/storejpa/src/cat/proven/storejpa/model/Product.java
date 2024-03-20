package cat.proven.storejpa.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.getAllProducts", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.getProductByCode", query = "SELECT p FROM Product p WHERE p.code = :code"),
        @NamedQuery(name = "Product.getProductsByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
        @NamedQuery(name = "Product.getProductsByStock", query = "SELECT p FROM Product p WHERE p.stock = :stock"),
        @NamedQuery(name = "Product.getProductsByCategoryId", query = "SELECT p FROM Product p WHERE p.category.id = :categoryId")
})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String code;

    private double price;

    private double stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructor, getters, setters, y otros métodos según sea necesario

    // ...

    public Product(long id, String code, double price, double stock, Category category) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Product() {
    }

    public Product(long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
    
    
}
