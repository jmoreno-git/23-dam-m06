package cat.proven.storejpa;

import cat.proven.storejpa.model.Category;
import cat.proven.storejpa.model.Product;
import cat.proven.storejpa.model.StoreModel;

/**
 *
 * @author ProvenSoft
 */
public class StoreJPATester {

    private StoreModel model;

    public StoreJPATester() {
        model = new StoreModel();
    }
    
    public static void main(String[] args) {
        StoreJPATester tester = new StoreJPATester();
        //
        tester.model.restoreData();
        //
        tester.testSearchAllCategories();
        tester.testSearchAllProducts();
        //
        tester.testCreateCategory();
        tester.model.searchAllCategories().forEach(System.out::println);
        //
        tester.testUpdateCategory();
        tester.model.searchAllCategories().forEach(System.out::println);  
        //
        tester.testDeleteCategory();
        tester.model.searchAllCategories().forEach(System.out::println);
        //
        tester.testSearchAllProducts();
        //
        tester.testCreateProduct();
        tester.model.searchAllCategories().forEach(System.out::println);
        tester.model.searchAllProducts().forEach(System.out::println);
        //
        tester.testUpdateProduct();
        tester.model.searchAllCategories().forEach(System.out::println);
        tester.model.searchAllProducts().forEach(System.out::println);
        //
        tester.testDeleteProduct();
        tester.model.searchAllCategories().forEach(System.out::println);
        tester.model.searchAllProducts().forEach(System.out::println);
        //
        tester.testDeleteCategoryWithProducts();
        //
        tester.relateCategoryWithProduct();
        //
        tester.model.restoreData();
    }
    
    public void testSearchAllCategories() {
        //search all categories and show
        System.out.println("Test: Search all categories");
        model.searchAllCategories().forEach(System.out::println);
    }
    
    public void testCreateCategory() {
        //create new category
        System.out.println("Test: Create new category");
        Category cat = new Category("c07", "c07_desc");
        cat = model.createCategory(cat);
        System.out.println("Category after been added: "+cat); 
        model.getEntityManager().detach(cat);
    }
    
    public void testUpdateCategory() {
        //modify category
        System.out.println("Test: Modify category");
        Category cat = new Category("c07", "c07_desc");
        cat.setName("c07_nouname");
        cat = model.updateCategory(cat);
        System.out.println("Category after modified: "+cat); 
        model.getEntityManager().detach(cat);
    }
    
    public void testDeleteCategory() {
        //delete category
        System.out.println("Test: Delete category");
        Category cat = new Category("c07", "c07_desc");
        model.deleteCategory(cat);        
    }
    
    public void testSearchAllProducts() {
        //search all categories and show
        System.out.println("Test: Search all products");
        model.searchAllProducts().forEach(System.out::println);
    }    
    
    public void testCreateProduct() {
        //create a new product
        System.out.println(">>Create a new product");
        Product prod = new Product(0, "a11", "a11_desc", 111.0, new Category("c06"));
        prod = model.createProduct(prod);
        System.out.println("Product after been added: "+prod); 
        model.getEntityManager().detach(prod);
    }
    
    public void testUpdateProduct() {
        //modify product
        System.out.println("Test: Modify product");
        Product prod = model.searchProductByCode("a11");
        prod.setDescription("a21_name");
        prod.setPrice(121.0);
        prod.setCategory(new Category("c05"));
        prod = model.updateProduct(prod);
        System.out.println("Product after modified: "+prod); 
        model.getEntityManager().detach(prod);        
    }
    
    public void testDeleteProduct() {
        //delete product
        System.out.println("Test: Delete product");
        Product prod = model.searchProductByCode("a11");
        model.deleteProduct(prod);         
    }
    
    public void testDeleteCategoryWithProducts() {
        System.out.println("Test: Delete category with products");
        //create category
        Category cat = new Category("c07", "c07_desc");
        cat = model.createCategory(cat);
        System.out.println("Category after been added: "+cat);
        //create product of that category
        Product prod = new Product(0, "a11", "a11_desc", 111.0, new Category("c07"));
        prod = model.createProduct(prod);
        System.out.println("Product after been added: "+prod);
        //show data
        model.searchAllCategories().forEach(System.out::println);
        model.searchAllProducts().forEach(System.out::println);
        //delete category
        System.out.println("Delete category: ");
        cat = new Category("c07", "c07_desc");
        model.deleteCategory(cat);
        //show data
        model.searchAllCategories().forEach(System.out::println);
        model.searchAllProducts().forEach(System.out::println);
    }
    
    public void relateCategoryWithProduct() {
        System.out.println("Test: Relate category with products");
        //create category
        Category cat = new Category("c07", "c07_desc");
        cat = model.createCategory(cat);
        System.out.println("Category after been added: "+cat);
        //create product 
        Product prod = new Product(0, "a11", "a11_desc", 111.0, new Category("c06"));
        prod = model.createProduct(prod);
        System.out.println("Product after been added: "+prod);
        //show data
        model.searchAllCategories().forEach(System.out::println);
        model.searchAllProducts().forEach(System.out::println);
        //relate category and product
        System.out.println("Stablish relationship: ");
        model.attachProductToCategory(cat, prod);
        //show data
        model.searchAllCategories().forEach(System.out::println);
        model.searchAllProducts().forEach(System.out::println);
        //delete product (this is necessary when CASCADE.DELETE is not allowed)
        System.out.println("Delete product");
        model.deleteProduct(prod);
        //
        System.out.println("Delete category");
        model.deleteCategory(cat);
        //show data
        model.searchAllCategories().forEach(System.out::println);
        model.searchAllProducts().forEach(System.out::println);
    }
}
