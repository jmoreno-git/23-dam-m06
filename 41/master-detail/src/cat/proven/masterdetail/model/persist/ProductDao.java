
package cat.proven.masterdetail.model.persist;

import cat.proven.masterdetail.model.Product;
import cat.proven.persist.DaoList;

public class ProductDao extends DaoList<Product> {

    private static ProductDao instance;
    
    public ProductDao() {
        super(Product.class);
        loadTestData();
    }
    
    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

    private void loadTestData() {
        insert(new Product(1, "C01", "Desc01", 101, 11));
        insert(new Product(2, "C02", "Desc02", 102, 12));
        insert(new Product(3, "C03", "Desc03", 103, 13));
        insert(new Product(4, "C04", "Desc04", 104, 14));
        insert(new Product(5, "C05", "Desc05", 105, 15));
    }
    
}
