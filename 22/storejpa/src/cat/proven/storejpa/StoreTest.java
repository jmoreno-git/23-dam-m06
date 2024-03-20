package cat.proven.storejpa;

import cat.proven.storejpa.model.Category;
import cat.proven.storejpa.model.StoreService;

/**
 *
 * @author ProvenSoft
 */
public class StoreTest {

    public static void main(String[] args) {
        StoreService dbHelper = new StoreService();
        dbHelper.getAllCategories().forEach(System.out::println);
        Category cat1 = new Category(0, "cat12");
        dbHelper.createCategory(cat1);
        System.out.println(cat1);
        cat1.setCode("C009");
        System.out.println(cat1);
        dbHelper.updateCategory(cat1);
        System.out.println(cat1);
        dbHelper.deleteCategory(cat1.getId());
        System.out.println(cat1);
       dbHelper.getAllCategories().forEach(System.out::println);
    }
    
}
