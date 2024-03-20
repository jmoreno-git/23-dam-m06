package figures2;

/**
 *
 * @author jose
 */
public class Figura {
    
    private String nom;

    public Figura(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Figura{");
        sb.append("nom=").append(nom);
        sb.append('}');
        return sb.toString();
    }
    
    
}
