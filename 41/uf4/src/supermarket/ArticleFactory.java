package supermarket;

/**
 *
 * @author alumne
 */
public class ArticleFactory {
    public static Article readArticle(String type) {
        Article a = null;
        switch (type) {
            case "food":
                a = readFood();
                break;
            default:
                throw new AssertionError();
        }
        return a;
    }
    public static Article readFood() {
        Food a = null;
        //TODO
        //a = new Food();
        return a;
    }
    
}
