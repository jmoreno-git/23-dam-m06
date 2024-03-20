package cat.proven.storejpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ProvenSoft
 */
@Entity
@Table(name = "categories")
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCode", query = "SELECT c FROM Category c WHERE c.code = :code"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    //@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "category")
    private List<Product> products;

    public Category() {
        this.products = new ArrayList<>();
    }

    public Category(String code) {
        this.code = code;
        this.products = new ArrayList<>();
    }

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public Category(String code, String name, List<Product> products) {
        this.code = code;
        this.name = name;
        this.products = products;
    }

    public Category(Category other) {
        this.code = other.code;
        this.name = other.name;
        this.products = other.products;        
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
        //product.setCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        //product.setCategory(null);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Category{code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", products=");
        sb.append("[");
        for (Product p: products) {
            sb.append("Product{");
            sb.append("id="+p.getId());
            sb.append("}");
        }
        sb.append("]");
        sb.append('}');
        return sb.toString();
    }

}