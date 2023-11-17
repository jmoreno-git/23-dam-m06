package cat.proven.mvc.views;

/**
 *
 * @author ProvenSoft
 */
public class MvcMenu extends Menu {

    public MvcMenu() {
        title = "MVC example main menu";
        addOption(new Option("Exit application", "exit"));
        addOption(new Option("Saludar", "greet"));
    }
    
}
