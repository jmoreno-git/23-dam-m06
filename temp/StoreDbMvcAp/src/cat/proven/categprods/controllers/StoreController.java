package cat.proven.categprods.controllers;

import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.StoreModel;
import cat.proven.categprods.views.StoreView;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class StoreController {
    
    private StoreModel model;
    private StoreView view;

    public StoreController(StoreModel model) {
        this.model = model;
        this.view = new StoreView(this, model);
    }
    
    public void start() {
        view.display();
    }
    
    public void processAction(String action) {
        //System.out.println("Processing action "+action);
        if (action != null) {
            switch (action) {
                case "exit": //exit
                    exitApplication();
                    break;
                case "category/all":
                    listAllCategories();
                    break;
                case "category/add":
                    addCategory();
                    break;
                default:
                    view.displayMessage("Action not supported");
                    break;
            }
        }
    }
    
    /**
     * asks for confirmation and, if so, exits application.
     */
    public void exitApplication() {
        String answer = view.inputString("Sure to exit? ");
        if (answer.equalsIgnoreCase("yes")) {
            //System.exit(0);
            view.close();
        }
    }

    /**
     * get all categories from database and displays them.
     */
    private void listAllCategories() {
        try {
            //retrieve all categories
            List<Category> data = model.findAllCategories();
            //display result
            if (data != null) {
                view.displayList(data);
            } else {
                view.displayMessage("Null data");
            }
        } catch (StoreDalException ex) {
            //Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
            view.displayMessage("Error retrieving data");
        }
        
    }
    
    /**
     * asks the user to input data for the new category,
     * if correctly read, adds the category to the database,
     * preventing code duplicates, null objects, null codes.
     * and report result to user,.
     */
    public void addCategory() {
        Category category = view.inputCategory();
        if (category != null) {
            int result = model.addCategory(category);
            if (result == 1) {
                view.displayMessage("Category succeessfully added");
            } else {
                view.displayMessage("Error adding category");
            }
        } else {
            view.displayMessage("Error reading data");
        }
    }    
    
}
