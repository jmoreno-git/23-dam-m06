package cat.proven.warehouse.views;

import cat.proven.warehouse.controllers.WarehouseController;
import cat.proven.warehouse.model.Product;
import cat.proven.warehouse.model.Warehouse;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * View for warehouse application
 *
 * @author ProvenSoft
 */
public class WarehouseView {

    private final WarehouseController controller;
    private final Warehouse model;

    private final MainMenu mainMenu;
    private boolean exit;

    public WarehouseView(WarehouseController controller, Warehouse model) {
        this.controller = controller;
        this.model = model;
        this.mainMenu = new MainMenu();
    }

    public void display() {
        exit = false;
        do {
            mainMenu.show();
            String action = mainMenu.getSelectedOptionActionCommand();
            controller.processAction(action);
        } while (!exit);
    }

    /**
     * prompts a message to user and read answer
     *
     * @param message the message to display
     * @return user's answer
     */
    public String inputString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    /**
     * displays a message
     *
     * @param message the message to display
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * close the view
     */
    public void close() {
        this.exit = true;
    }

    /**
     * displays a list of data
     *
     * @param <T> data type to display
     * @param data the list to display
     */
    public <T> void displayMultiple(List<T> data) {
        if (data != null) {
            data.forEach(System.out::println);
            System.out.format("%d elements displayed\n", data.size());
        }
    }

    /**
     * displays one single element
     *
     * @param <T> data type to display
     * @param element the element to display
     */
    public <T> void displaySingle(T element) {
        if (element != null) {
            System.out.println(element);
        }
    }

    /**
     * asks data for a product
     *
     * @return a product object with entered data or null in case or error
     */
    public Product inputProduct() {
        //TODO
        Product p = null;
        try {
            String code = inputString("code: ");
            String name = inputString("name: ");
            String sPrice = inputString("price: ");
            String sContainerId = inputString("container id (0 for none): ");
            double price = Double.parseDouble(sPrice);
            long containerId = Long.parseLong(sContainerId);
            Product container = (containerId == 0) ? null : new Product(containerId);
            p = new Product(0, code, name, price, container);
        } catch (InputMismatchException ex) {
            p = null;
        }
        return p;
        //END TODO
    }

    /**
     * displays message to user according to error code
     *
     * @param code
     */
    public void displayError(int code) {
        //TODO
        String message = "";
//        switch (code) {
//            case 101:
//            //break;
//            case 102:
//            //break;
//            case 103:
//            //break;
//            case 104:
//            //break;
//            case 105:
//            //break;
//            default:
//                message = "unknown error";
//                break;
//        }
        //
        Map<Integer, String> errorMessages = initErrorMessages();
        message = errorMessages.get(code);
        //
        System.out.println(code + ": " + message);
    }

    /**
     * initializes error messages
     * @return a map relating codes to messages
     */
    private Map<Integer, String> initErrorMessages() {
        return Stream.of(new Object[][]{
            {101, "Unable to connect to database"},
            {102, "Insertion error"},
            {103, "Deletion error"},
            {104, "Selection error"},
            {105, "SQL error"},
            {106, "Database driver not found"},
            {107, "Data type error retrieved from query"}
        }).collect(Collectors.toMap(e -> (Integer) e[0], e -> (String) e[1]));
    }

}
