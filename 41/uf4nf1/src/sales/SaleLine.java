
package sales;

/**
 *
 * @author alumne
 */
public class SaleLine {
    private Article article;
    private int quantity;

    public SaleLine(Article article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SaleLine{");
        sb.append("article=").append(article);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
    
    
}
