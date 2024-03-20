package cat.proven.warehouse.model;

import cat.proven.utils.exceptions.PersistException;
import cat.proven.warehouse.model.persist.ProductDao;
import java.util.List;

/**
 * Data service for warehouse application (model)
 * @author ProvenSoft
 */
public class Warehouse {
    
    /**
     * DAO for product object
     */
    private final ProductDao productDao;

    public Warehouse() {
        this.productDao = new ProductDao();
    }
    
    /**
     * adds a new product to database
     * it prevents adding null objects or null codes
     * also prevents code duplicates
     * @param product the product to add
     * @return 1 if successful, 0 otherwise.
     * @throws PersistException in case of database error
     */
    public int addProduct(Product product) throws PersistException {
        return productDao.insert(product);
    }
       
    /**
     * finds all products in database
     * @return list with all products or null in case of error
     * @throws PersistException in case of database error
     */
    public List<Product> findAllProducts() throws PersistException {
        return productDao.selectAll();
    }
    
    /**
     * finds a product given its code
     * @param code the code to search
     * @return the product with given code or null if not found or in case of error
     * @throws PersistException in case of database error
     */
    public Product findProductByCode(String code) throws PersistException {
        Product result = null;
        if (code != null) {
            result = productDao.selectWhereCode(code);
        }
        return result;
    }
    
    /**
     * finds all products with given container
     * @param product the product container to search
     * @return list of products with given container
     * @throws PersistException in case of database error
     */
    public List<Product> findProductsByContainer(Product product) throws PersistException {
        List<Product> result = null;
        if (product != null) {
            result = productDao.selectWhereContainer(product);
        }
        return result;
    } 
    
    //TODO
    /**
     * finds a product with given id
     * @param id the id to find
     * @return product found or null if not found
     * @throws PersistException in case of database error
     */
    public Product findProductById(long id) throws PersistException {
        Product result = null;
        result = productDao.select(new Product(id));
        return result;
    }
    //END TODO
    
}
