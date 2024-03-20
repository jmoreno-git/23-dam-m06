
package cat.proven.masterdetail.model;

import cat.proven.masterdetail.model.persist.ProductDao;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    
    private final ProductDao pDao;

    public ProductModel() {
        pDao = ProductDao.getInstance();
    }
    
    public List<String> getProductCodes() {
        List<String> codes = new ArrayList<>();
        List<Product> products = pDao.selectAll();
        products.forEach((t) -> {
            codes.add(t.getCode());
        });
        return codes;
    }
    
    public Product getProductByCode(String code) {
        Product p = null;
        List<Product> result = pDao.selectWhere("code", code);
        if ((result != null) && (!result.isEmpty())) {
            p = result.get(0);
        }
        return p;
    }
}
