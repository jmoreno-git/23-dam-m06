
package cat.proven.storejpa.model;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jasmm
 */
public class StoreServiceTest {
    
    public StoreServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createCategory method, of class StoreService.
     */
    @Test
    public void testCreateCategory() {
        System.out.println("createCategory");
        Category category = null;
        StoreService instance = new StoreService();
        Category expResult = null;
        Category result = instance.createCategory(category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryById method, of class StoreService.
     */
    @Test
    public void testGetCategoryById() {
        System.out.println("getCategoryById");
        long categoryId = 0L;
        StoreService instance = new StoreService();
        Category expResult = null;
        Category result = instance.getCategoryById(categoryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCategories method, of class StoreService.
     */
    @Test
    public void testGetAllCategories() {
        System.out.println("getAllCategories");
        StoreService instance = new StoreService();
        List<Category> expResult = null;
        List<Category> result = instance.getAllCategories();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCategory method, of class StoreService.
     */
    @Test
    public void testUpdateCategory() {
        System.out.println("updateCategory");
        Category category = null;
        StoreService instance = new StoreService();
        instance.updateCategory(category);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCategory method, of class StoreService.
     */
    @Test
    public void testDeleteCategory() {
        System.out.println("deleteCategory");
        long categoryId = 0L;
        StoreService instance = new StoreService();
        instance.deleteCategory(categoryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createProduct method, of class StoreService.
     */
    @Test
    public void testCreateProduct() {
        System.out.println("createProduct");
        Product product = null;
        StoreService instance = new StoreService();
        Product expResult = null;
        Product result = instance.createProduct(product);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductById method, of class StoreService.
     */
    @Test
    public void testGetProductById() {
        System.out.println("getProductById");
        long productId = 0L;
        StoreService instance = new StoreService();
        Product expResult = null;
        Product result = instance.getProductById(productId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllProducts method, of class StoreService.
     */
    @Test
    public void testGetAllProducts() {
        System.out.println("getAllProducts");
        StoreService instance = new StoreService();
        List<Product> expResult = null;
        List<Product> result = instance.getAllProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProduct method, of class StoreService.
     */
    @Test
    public void testUpdateProduct() {
        System.out.println("updateProduct");
        Product product = null;
        StoreService instance = new StoreService();
        instance.updateProduct(product);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProduct method, of class StoreService.
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        long productId = 0L;
        StoreService instance = new StoreService();
        instance.deleteProduct(productId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoriesByCode method, of class StoreService.
     */
    @Test
    public void testGetCategoriesByCode() {
        System.out.println("getCategoriesByCode");
        String code = "";
        StoreService instance = new StoreService();
        List<Category> expResult = null;
        List<Category> result = instance.getCategoriesByCode(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductsByCode method, of class StoreService.
     */
    @Test
    public void testGetProductsByCode() {
        System.out.println("getProductsByCode");
        String code = "";
        StoreService instance = new StoreService();
        List<Product> expResult = null;
        List<Product> result = instance.getProductsByCode(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductsByPrice method, of class StoreService.
     */
    @Test
    public void testGetProductsByPrice() {
        System.out.println("getProductsByPrice");
        double price = 0.0;
        StoreService instance = new StoreService();
        List<Product> expResult = null;
        List<Product> result = instance.getProductsByPrice(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductsByStock method, of class StoreService.
     */
    @Test
    public void testGetProductsByStock() {
        System.out.println("getProductsByStock");
        double stock = 0.0;
        StoreService instance = new StoreService();
        List<Product> expResult = null;
        List<Product> result = instance.getProductsByStock(stock);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductsByCategoryId method, of class StoreService.
     */
    @Test
    public void testGetProductsByCategoryId() {
        System.out.println("getProductsByCategoryId");
        long categoryId = 0L;
        StoreService instance = new StoreService();
        List<Product> expResult = null;
        List<Product> result = instance.getProductsByCategoryId(categoryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class StoreService.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        StoreService instance = new StoreService();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
