package supermarket;

import java.util.Objects;

/**
 *
 * @author alumne
 */
public class Article {
    private String name;
    private String maker;
    private double price;
    private String eanCode;

    public Article(String name, String maker, double price, String eanCode) {
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.eanCode = eanCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.eanCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        return Objects.equals(this.eanCode, other.eanCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Article{");
        sb.append("name=").append(name);
        sb.append(", maker=").append(maker);
        sb.append(", price=").append(price);
        sb.append(", eanCode=").append(eanCode);
        sb.append('}');
        return sb.toString();
    }
    
}
