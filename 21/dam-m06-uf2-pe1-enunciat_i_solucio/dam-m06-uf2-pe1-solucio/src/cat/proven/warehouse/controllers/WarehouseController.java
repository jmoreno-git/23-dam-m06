package cat.proven.warehouse.controllers;

import cat.proven.utils.exceptions.PersistException;
import cat.proven.warehouse.model.Product;
import cat.proven.warehouse.model.Warehouse;
import cat.proven.warehouse.views.WarehouseView;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller for warehouse application
 *
 * @author ProvenSoft
 */
public class WarehouseController {

    private final Warehouse model;
    private final WarehouseView view;

    public WarehouseController(Warehouse model) {
        this.model = model;
        this.view = new WarehouseView(this, model);
    }

    public void start() {
        view.display();
    }

    public void processAction(String action) {
        if (action != null) {
            switch (action) {
                case "exit" ->
                    exitApplication();
                case "listAllProducts" ->
                    listAllProducts();
                case "listProductByCode" ->
                    listProductByCode();
                case "addProduct" ->
                    addProduct();
                case "listProductsByContainer" ->
                    listProductsByContainer();
                default ->
                    view.displayMessage("Action not supported");
            }
        }
    }

    /**
     * asks for confirmation and, if so, exits application.
     */
    public void exitApplication() {
        String answer = view.inputString("Sure to exit? ");
        if (answer.equalsIgnoreCase("yes")) {
            view.close();
        }
    }

    /**
     * get all products from database and displays them.
     */
    private void listAllProducts() {
        try {
            //retrieve all products
            List<Product> data = model.findAllProducts();
            //display result
            if (data != null) {
                view.displayMultiple(data);
            } else {
                view.displayMessage("Null data");
            }
        } catch (PersistException ex) {
            //Logger.getLogger(WarehouseController.class.getName()).log(Level.SEVERE, null, ex);
            view.displayError(ex.getCode());
        }

    }

    public void addProduct() {
        //TODO
        Product product = view.inputProduct();
        if (product != null) {
            try {
                //check that code does not exist
                Product p = model.findProductByCode(product.getCode());
                if (p == null) {
                    int result = model.addProduct(product);
                    String message = (result == 1) ? "Product successfully added" : "Product not added";
                    System.out.println(message);
                } else {
                    view.displayMessage("Product not added to avoid code duplication");
                }
            } catch (PersistException ex) {
                //Logger.getLogger(WarehouseController.class.getName()).log(Level.SEVERE, null, ex);
                view.displayError(ex.getCode());
            }
        } else {
            view.displayMessage("Invalid data. Product not read");
        }
        //END TODO
    }

    private void listProductsByContainer() {
        //TODO
        try {
            String sContainerId = view.inputString("container id: ");
            try {
                long containerId = Long.parseLong(sContainerId);
                //search container (check for existence)
                Product container = model.findProductById(containerId);
                if (container != null) {
                    List<Product> products = model.findProductsByContainer(container);
                    view.displayMultiple(products);
                } else {
                    view.displayMessage("No product found with id: "+containerId);
                }
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid data for container id");
            }
        } catch (PersistException ex) {
            //Logger.getLogger(WarehouseController.class.getName()).log(Level.SEVERE, null, ex);
            view.displayError(ex.getCode());
        }
        //END TODO
    }

    private void listProductByCode() {
        //TODO
        try {
            String code = view.inputString("code: ");
            Product p = model.findProductByCode(code);
            if (p != null) {
                view.displaySingle(p);
            } else {
                view.displayMessage(String.format("Product with code %s not found%n", code));
            }
        } catch (PersistException ex) {
            //Logger.getLogger(WarehouseController.class.getName()).log(Level.SEVERE, null, ex);
            view.displayError(ex.getCode());
        }
        //END TODO
    }

}
