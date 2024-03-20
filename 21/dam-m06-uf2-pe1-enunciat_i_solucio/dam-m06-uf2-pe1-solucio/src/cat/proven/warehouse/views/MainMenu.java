
package cat.proven.warehouse.views;

import cat.proven.utils.menu.Menu;
import cat.proven.utils.menu.Option;

/**
 * Main menu for warehouse application
 * @author ProvenSoft
 */
public class MainMenu extends Menu {

    public MainMenu() {
        title = "Warehouse application";
        addOption( new Option("Exit", "exit") );
        addOption( new Option("List all products", "listAllProducts") );
        addOption( new Option("Search product by code", "listProductByCode") );
        addOption( new Option("Add a new product", "addProduct") );
        addOption( new Option("Search products by container", "listProductsByContainer") );
    }
    
}
