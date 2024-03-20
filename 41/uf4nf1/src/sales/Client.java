
package sales;

/**
 *
 * @author alumne
 */
public class Client {
    private String nif;
    private String name;

    public Client(String nif, String name) {
        this.nif = nif;
        this.name = name;
    }

    public Client() {
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client{");
        sb.append("nif=").append(nif);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
    
    
}
