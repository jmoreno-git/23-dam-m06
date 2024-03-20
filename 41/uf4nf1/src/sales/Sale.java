
package sales;

/**
 *
 * @author alumne
 */
public class Sale {
    private Client client;
    private SaleLine[] lines;

    public Sale(Client client, SaleLine[] lines) {
        this.client = client;
        this.lines = lines;
    }

    public Sale(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SaleLine[] getLines() {
        return lines;
    }

    public void setLines(SaleLine[] lines) {
        this.lines = lines;
    }
    
}
