
package cat.proven.warehouse;

import cat.proven.warehouse.controllers.WarehouseController;
import cat.proven.warehouse.model.Warehouse;

/**
 * Main class to start warehouse application
 * @author ProvenSoft
 */
public class MainMvc {

    public static void main(String[] args) {
        //TODO
        Warehouse model = new Warehouse();
        WarehouseController controller = new WarehouseController(model);
        controller.start();
        //END TODO
    }
    
}
