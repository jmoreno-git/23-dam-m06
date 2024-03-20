package cat.proven.storejpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Jose
 */
@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "Category.getAllCategories", query = "SELECT c FROM Category c"),
        @NamedQuery(name = "Category.getCategoryByCode", query = "SELECT c FROM Category c WHERE c.code = :code")
})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    // Constructor, getters, setters, y otros métodos según sea necesario
    public Category(long id, String code) {
        this.id = id;
        this.code = code;
        this.products = new ArrayList<>();
    }

    public Category() {
        this.products = new ArrayList<>();
    }

    public Category(long id) {
        this.id = id;
        this.products = new ArrayList<>();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
    // ...

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Category{");
        sb.append("id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }


    
    
}
