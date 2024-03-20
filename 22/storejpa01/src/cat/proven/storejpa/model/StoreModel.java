package cat.proven.storejpa.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author ProvenSoft
 */
public class StoreModel {

    /**
     * EntityManagerFactory to create EntityManager
     */
    private final EntityManagerFactory emFactory;
    /**
     * flag to treat properly cascade restrictions
     */
    private final boolean IS_PRODUCT_CATEGORY_NULLABLE;

    public StoreModel() {
        this.IS_PRODUCT_CATEGORY_NULLABLE = false;
        this.emFactory = Persistence.createEntityManagerFactory("StoreJPAPU");
    }

    /**
     * creates entity manager
     *
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
    
    /*********** CATEGORY METHODS ***********/
    
    /**
     * finds a category by code
     *
     * @param code the code to find
     * @return category found or null if not found
     */
    public Category searchCategoryByCode(String code) {
        EntityManager em = getEntityManager();
        Category category = null;
        try {
            category = em.find(Category.class, code);  //returns null if not found.
        } finally {
            em.close();
        }
        return category;
    }

    /**
     * finds all categories
     *
     * @return clist of all categories or null in case of error
     */
    public List<Category> searchAllCategories() {
        EntityManager em = getEntityManager();
        List<Category> categories = null;
        try {
            TypedQuery<Category> query = em.createNamedQuery("Category.findAll",Category.class);
            categories = query.getResultList();
        } finally {
            em.close();
        }
        return categories;
    }
    
    
     /**
     * Finds all categories with name 
     * @param name to search
     * @return clist of categories with name. If no category is found, it returns and empty clist.
     */
    public List<Category>searchCategoriesByName (String name){
        List<Category> result= new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Category> query = em.createNamedQuery("Category.findByName", Category.class);
            query.setParameter("name", name);
            result = query.getResultList();
        } finally {
            em.close();
        }
        return result;
    }
    
    public Category createCategory(Category category) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return category;
    }
    
    public Category updateCategory(Category category) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        } 
        return category;
    }
    
    public void deleteCategory(Category category) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category categoryManaged = entityManager.find(Category.class, category.getCode());
            entityManager.remove(categoryManaged);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
    }
    
    /*********** PRODUCT METHODS ***********/    
    
    /**
     * finds a product by id
     *
     * @param id the id to find
     * @return product found or null if not found
     */
    public Product searchProductById(Integer id) {
        EntityManager em = getEntityManager();
        Product product = null;
        try {
            product = em.find(Product.class, id);  //returns null if not found.
        } finally {
            em.close();
        }
        return product;
    }

    public Product searchProductByCode(String code) {
        Product result= null;
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findByCode", Product.class);
            query.setParameter("code", code);
            result = query.getSingleResult();
        } finally {
            em.close();
        }
        return result;       
    }    
    
    /**
     * finds all products
     *
     * @return clist of all products or null in case of error
     */
    public List<Product> searchAllProducts() {
        EntityManager em = getEntityManager();
        List<Product> products = null;
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
            products = query.getResultList();
        } finally {
            em.close();
        }
        return products;
    }
        
    
    public Product createProduct(Product product) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category c = product.getCategory();
            if (c != null) {
                c = entityManager.getReference(Category.class, product.getCategory().getCode());
                product.setCategory(c);
                c.addProduct(product);                
            }
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return product;
    }    

    public Product updateProduct(Product product) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            attachProductToCategory(product.getCategory(), product);
            entityManager.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        } 
        return product;
    }    

    public void deleteProduct(Product product) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Product productManaged = entityManager.find(Product.class, product.getId());
            Category c = entityManager.getReference(Category.class, product.getCategory().getCode());
            c.removeProduct(product);
            entityManager.remove(productManaged);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
    }
    
    public void restoreData() {
        final String maxCatCode = "c06";
        final long maxProdId = 10L;
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            //remove categories added in tests
            List<Category> clist = searchAllCategories();
            clist.forEach((t) -> {
                if (t.getCode().compareToIgnoreCase(maxCatCode) > 0) {
                    Category c = entityManager.find(Category.class, t.getCode());
                    entityManager.remove(c);
                }
            });
            //remove products added in tests
            List<Product> plist = searchAllProducts();
            plist.forEach((t) -> {
                if (t.getId()>maxProdId) {
                    Product p = entityManager.find(Product.class, t.getId());
                    entityManager.remove(p);
                }
            });
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }       
    }
    
    public void attachProductToCategory(Category category, Product product) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Product managedProduct = entityManager.find(Product.class, product.getId());
            //
            Category cOld = managedProduct.getCategory();
            if (cOld != null) {
                cOld = entityManager.getReference(Category.class, managedProduct.getCategory().getCode());
                cOld.removeProduct(product);
                entityManager.merge(cOld);                
            }
            //
            Category cNew = entityManager.getReference(Category.class, category.getCode());
            cNew.addProduct(product);
            entityManager.merge(cNew);
            //
            product.setCategory(category);
            entityManager.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
    }
    
    public void detachProductFromCategory(Category category, Product product) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Product managedProduct = entityManager.find(Product.class, product.getId());
            //
            Category cOld = entityManager.getReference(Category.class, managedProduct.getCategory().getCode());
            cOld.removeProduct(product);
            entityManager.merge(cOld);
            //
            product.setCategory(null);
            entityManager.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }        
    }    
    
}