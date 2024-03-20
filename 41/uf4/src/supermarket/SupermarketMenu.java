package supermarket;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class SupermarketMenu {
    private String title;
    private String[] options = {
        "Exit", "List all articles", 
        "Add a new article", "Search article by EAN code"
    };

    public SupermarketMenu(String title) {
        this.title = title;
    }
    
    /**
     * displays menu and reads option number from user
     * @return option selected by user or -1 in case or error or invalid option
     */
    public int showMenu() {
        Scanner scan = new Scanner(System.in);
        int option;
        System.out.format("===== %s =====%n", title);
        for (int i = 0; i < options.length; i++) {
            System.out.format("%d. %s%n", i, options[i]);
        }
        try {
            System.out.print("Select an option: ");
            option = scan.nextInt();  
            //validate interval
            if ((option<0) || (option>=options.length)) {  //out of interval
                option = -1;
            }
        } catch (InputMismatchException e) {
            scan.next();
            option = -1;
        }
        return option;
    }
    
}
