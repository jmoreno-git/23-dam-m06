package supermarket;

import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class SupermarketMain {
    
    private Supermarket mySupermarket;
    private final Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        SupermarketMain sm = new SupermarketMain();
        sm.run();
    }

    private void run() {
        //instantiate supermarket
        mySupermarket = new Supermarket("La botigueta de la cantonada", 10);
        //generate articles
        mySupermarket.generateArticles();
        //instantiate menu
        SupermarketMenu menu = new SupermarketMenu("Supermarket main menu");
        //control loop (interact with user)
        boolean exit = false;
        do {
            //show menu and read user's choice
            int selectedOption = menu.showMenu();
            //process option
            switch (selectedOption) {
                case 0: //exit
                    exit = true;
                    break;
                case 1: //list all articles
                    doListAllArticles();
                    break;
                case 2: //add a new article
                    doAddANewArticle();
                    break;
                case 3: //search article by EAN code
                    doSearchArticle();
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (!exit);
    }

    private void doListAllArticles() {
        Article[] artList = mySupermarket.getArticles();
        for (Article art : artList) {
            System.out.println(art);
        }
        System.out.format("%d articles shown%n", artList.length);
    }

    /**
     * asks the type of article to add,
     * reads data for the new article,
     * if successfully read, tries to add the article and reports result to user
     * if not successfully read, reports error to user
     */
    private void doAddANewArticle() {
        //read type
        System.out.print("Input type: ");
        String type = scan.next();
        //read article
        Article articleToAdd = ArticleFactory.readArticle(type);
        if (articleToAdd != null) {
            boolean success = mySupermarket.addArticle(articleToAdd);
            if (success) {
                System.out.println("Article successfully added");
            } else {
                System.out.println("Article not added");
            } 
        } else {
            System.out.println("Error reading article");
        }
    }

    /**
     * asks the user to input EAN code of article to search,
     * searches the article,
     * if found, displays article,
     * if not found reports error to user.
     */
    private void doSearchArticle() {
        System.out.print("Input EAN code: ");
        String eanCodeToSearch = scan.next();
        Article found = mySupermarket.searchArticle(eanCodeToSearch);
        if (found != null) {  //found
            System.out.println("Found: "+found);
        } else {  //not found
            System.out.println("There is no article with EAN code: "+eanCodeToSearch);
        }
    }
    
}
