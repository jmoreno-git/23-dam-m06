
package supermarket;

/**
 *
 * @author alumne
 */
public class Food extends Article {
    private String data;

    public Food(String name, String maker, double price, String eanCode, String data) {
        super(name, maker, price, eanCode);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Food{");
        sb.append(super.toString());
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
    
}
