
package sales;

import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class SalesMain {
    
    private Article[] storeArticles;
    Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        SalesMain ap = new SalesMain();
        ap.run();
    }
    
    private void run() {
        generateArticles();
        boolean exit = false;
        //
        do {
            //read client data from user
            Client client = readClient();
            //ask user number of different storeArticles to buy
            int maxArticles = Integer.parseInt(readString("Number of articles: "));
            SaleLine[] lines = readLines(maxArticles);
            //instantiate sale
            Sale sale = new Sale(client, lines);
            printBill(sale);
            //ask if continue or exit
            String answer = readString("Another client? (Y/N)");
            exit = answer.equalsIgnoreCase("N");
        } while (!exit);
        //
        System.out.println("Goodbye!");
    }
    
    public void printBill(Sale sale) {
        //TODO
        System.out.println("Not implemented yet!");
    }

    public String readString(String message) {
        System.out.print(message);
        return scan.next();
    }
    
    public Client readClient() {
        Client client = null;
        String nif = readString("Client nif: ");
        String name = readString("Client name: ");
        client = new Client(nif, name);
        return client;
    }

    private void generateArticles() {
        storeArticles = new Article[5];
        storeArticles[0] = new Article("A01", "Desc01", 101.0);
        storeArticles[1] = new Article("A02", "Desc02", 102.0);
        storeArticles[2] = new Article("A03", "Desc03", 103.0);
        storeArticles[3] = new Article("A04", "Desc04", 104.0);
        storeArticles[4] = new Article("A05", "Desc05", 105.0);
    }

    public SaleLine[] readLines(int maxlines) {
        SaleLine[] lines = new SaleLine[maxlines];
        for (int i = 0; i < maxlines; i++) {
            SaleLine line = readLine();
            lines[i] = line;
        }
        return lines;
    }

    public SaleLine readLine() {
        SaleLine line = null;
        String code = readString("Article code: ");
        int quant = Integer.parseInt(readString("Quantity: "));
        //search article in store articles
        Article article = findArticle(code);
        if (article != null) {
            line = new SaleLine(article, quant);
        }
        return line;
    }
    
    public Article findArticle(String code) {
        Article a = null;
        for (Article elem : storeArticles) {
            if (elem.getCode().equalsIgnoreCase(code)) {
                a = elem;
                break;
            }
        }
        return a;
    }
}
