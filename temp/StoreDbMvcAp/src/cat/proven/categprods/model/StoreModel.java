package cat.proven.categprods.model;

import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.persist.CategoryDao;
import cat.proven.categprods.model.persist.ProductDao;
import java.util.List;

/**
 *
 * @author ProvenSoft
 */
public class StoreModel {
    
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public StoreModel() {
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();
    }
    
    /**
     * adds a new category to database
     * it prevents adding null categories or null codes
     * also prevents code duplicates
     * @param category the category to add
     * @return 1 if successful, 0 otherwise.
     */
    public int addCategory(Category category) {
        int result = 0;
        if (category != null) {
            if (category.getCode() != null) {
                Category c = categoryDao.selectWhereCode(category.getCode());
                if (c == null) {
                   result = categoryDao.insert(category);
                }    
            }
        }
        return result;
    }
    
    /**
     * finds all categories in database
     * @return list with all categories or null in case of error
     * @throws StoreDalException in case of error connecting to database
     */
    public List<Category> findAllCategories() throws StoreDalException {
        return categoryDao.selectAll();
    }
    
    /**
     * finds a product given its code
     * @param code the code to search
     * @return the product with given code or null if not found or in case of error
     */
    public Product findProductByCode(String code) {
        Product result = null;
        if (code != null) {
            result = productDao.selectWhereCode(code);
        }
        return result;
    }
    
}
