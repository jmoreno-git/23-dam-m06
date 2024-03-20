package cat.proven.categprods.model;

import cat.proven.categprods.model.persist.CategoryDao;
import cat.proven.categprods.model.persist.ProductDao;
import java.util.List;

/**
 * Model for store application. Provides data services.
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
     * Data services related to category
     */
    
    /**
     * adds a category to data source, preventing duplicates in unique keys and
     * null values
     *
     * @param category the category to add
     * @return result code: 1 for success, 0 if fail (change as necessary)
     */
    public int addCategory(Category category) {
        int result = 0;
        if (category != null) { 
            //perform proper validations before attempting insertion
            boolean dataValid = true;
            String code = category.getCode();
            if (code==null) dataValid = false; //code must not be null
            else { //assess that code does not exist
                Category c = categoryDao.selectWhereCode(code);
                if (c != null) dataValid = false;
            }
            if (dataValid) {  //perform insertion
                result = categoryDao.insert(category);
            }
        }
        return result;
    }

    /**
     * modifies a category in the data source, performing proper validations
     *
     * @param oldC the actual category to update
     * @param newC the new values to update
     * @return result code: 1 for success, 0 if fail (change as necessary)
     */
    public int modifyCategory(Category oldC, Category newC) {
        int result = 0;
        if ((oldC != null) && (newC != null)) { //perform proper validations before attempting insertion
            result = categoryDao.update(oldC, newC);
        }
        return result;
    }

    /**
     * finds all categories in data source
     *
     * @return list with all categories or null in case of error
     */
    public List<Category> findAllCategories() {
        return categoryDao.selectAll();
    }

    /**
     * finds a category with the given code
     *
     * @param code the code to find
     * @return category found or null if not found or in case of error
     */
    public Category findCategoryByCode(String code) {
        Category c = null;
        if (code != null) {
            c = categoryDao.selectWhereCode(code);
        }
        return c;
    }

    /**
     * Data services related to product
     */
    /**
     * adds a product to data source, preventing duplicates in unique keys and
     * null values
     *
     * @param product the category to add
     * @return result code: 1 for success, 0 if fail (change as necessary)
     */
    public int addProduct(Product product) {
        int result = 0;
        if (product != null) {
            //perform proper validations before attempting insertion
            boolean dataValid = true;
            String code = product.getCode();
            if (code==null) dataValid = false; //code must not be null
            else { //assess that code does not exist
                Product p = productDao.selectWhereCode(code);
                if (p != null) dataValid = false;
            }
            //get category from database
            Category cat = categoryDao.select(product.getCategory());
            if (cat == null) dataValid = false;  //category must exist
            if (dataValid) {  //perform insertion
                result = productDao.insert(product);
            }
        }
        return result;
    }

    /**
     * finds all products in data sources
     *
     * @return list of all products or null in case of error
     */
    public List<Product> findAllProducts() {
        return productDao.selectAll();
    }

    /**
     * finds a product with the given code
     *
     * @param code the code to find
     * @return category found or null if not found or in case of error
     */
    public Product findProductByCode(String code) {
        Product c = null;
        if (code != null) {
            c = productDao.selectWhereCode(code);
        }
        return c;
    }

    /**
     * Data services related to category-product relationship
     */
    /**
     * finds all products belonging to given category
     *
     * @param category the category whose products are being searched
     * @return list of products of given category or null in case of error
     */
    public List<Product> findProductsByCategory(Category category) {
        List<Product> result = null;
        if (category != null) {
            result = productDao.selectWhereCategory(category);
        }
        return result;
    }

    /**
     * finds a product and retrieves all its information, including that
     * corresponding to its category
     *
     * @param product the product to find
     * @return product found or null in case of error
     */
    public Product findProductWithCategory(Product product) {
        Product p = null;
        if (product != null) {
            p = productDao.select(product);
            if (p != null) {
                Category c = categoryDao.select(p.getCategory());
                if (c != null) {
                    p.setCategory(c);
                }
            }
        }
        return p;
    }
}
