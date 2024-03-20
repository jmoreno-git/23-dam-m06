package supermarket;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author alumne
 */
public class Supermarket {
    private String name;
    private Article[] articles;
    private int maxArticles = 100;
    private int numArticles;

    public Supermarket(String name, int maxArticles) {
        this.name = name;
        this.maxArticles = maxArticles;
        this.articles = new Article[maxArticles];
        this.numArticles = 0;
    }

    public String getName() {
        return name;
    }

    public Article[] getArticles() {
        return Arrays.copyOf(articles, numArticles);
    }

    public int getMaxArticles() {
        return maxArticles;
    }

    public int getNumArticles() {
        return numArticles;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * adds an article,
     * prevents EAN duplicates and null articles
     * @param article the article to add
     * @return true is successfully added, false otherwise
     */
    public boolean addArticle(Article article) {
        boolean b = false;
        if (article != null) {  //article not null
            if (searchArticle(article.getEanCode()) == null) { //not yet in array
                articles[numArticles] = article;
                numArticles++;
                b = true;
            }
        }
        return b;
    }
    
    /**
     * searches an article with the given EAN code
     * @param eanCode the EAN code to search
     * @return the article found or null if not found
     */
    public Article searchArticle(String eanCode) {
        Article article = null;
        for (int i=0; i<numArticles; i++) {
            if (articles[i].getEanCode().equals(eanCode)) {
                article = articles[i];
                break;
            }
        }
        return article;
    }
    
    public void generateArticles() {
        IntStream.range(0, 8)
            .forEach((value) -> {
                Article a = new Article(
                   String.format("name%02d", value),
                   String.format("maker%02d", value),
                   100.0+value,
                   String.format("EAN%02d", value)
                );
                addArticle(a);
            });
    }
    
}
