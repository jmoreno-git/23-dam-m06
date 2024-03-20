package cat.proven.storejpa.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StoreService {
    private final EntityManagerFactory entityManagerFactory;

    public StoreService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("storejpaPU"); // Nom de la unitat de persistència
    }

    // Mètodes CRUD per a Category

    public Category createCategory(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

    public Category getCategoryById(long categoryId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Category category = entityManager.find(Category.class, categoryId);
        entityManager.close();
        return category;
    }

    public List<Category> getAllCategories() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //List<Category> categories = entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        TypedQuery<Category> query = entityManager.createNamedQuery("Category.getAllCategories", Category.class);
        List<Category> categories = query.getResultList();
        entityManager.close();
        return categories;
    }

    public void updateCategory(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
    }

    public void deleteCategory(long categoryId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Category category = entityManager.find(Category.class, categoryId);
            entityManager.remove(category);
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

    // Mètodes CRUD per a Product

    public Product createProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
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

    public Product getProductById(long productId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, productId);
        entityManager.close();
        return product;
    }

    public List<Product> getAllProducts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //List<Product> products = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.getAllProducts", Product.class);
        List<Product> products = query.getResultList();
        entityManager.close();
        return products;
    }

    public void updateProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
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

    public void deleteProduct(long productId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Product product = entityManager.find(Product.class, productId);
            entityManager.remove(product);
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

    // Consultes per filtrar per camps a Category

    public List<Category> getCategoriesByCode(String code) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.code = :code", Category.class);
        TypedQuery<Category> query = entityManager.createNamedQuery("Category.getCategoryByCode", Category.class);
        query.setParameter("code", code);
        List<Category> categories = query.getResultList();
        entityManager.close();
        return categories;
    }

    // Consultes per filtrar per camps a Product

    public List<Product> getProductsByCode(String code) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.code = :code", Product.class);
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.getProductByCode", Product.class);
        query.setParameter("code", code);
        List<Product> products = query.getResultList();
        entityManager.close();
        return products;
    }

    public List<Product> getProductsByPrice(double price) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.price = :price", Product.class);
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.getProductsByPrice", Product.class);
        query.setParameter("price", price);
        List<Product> products = query.getResultList();
        entityManager.close();
        return products;
    }

    public List<Product> getProductsByStock(double stock) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.stock = :stock", Product.class);
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.getProductsByStock", Product.class);
        query.setParameter("stock", stock);
        List<Product> products = query.getResultList();
        entityManager.close();
        return products;
    }

    public List<Product> getProductsByCategoryId(long categoryId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.category.id = :categoryId", Product.class);
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.getProductsByCategoryId", Product.class);
        query.setParameter("categoryId", categoryId);
        List<Product> products = query.getResultList();
        entityManager.close();
        return products;
    }

    public void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
