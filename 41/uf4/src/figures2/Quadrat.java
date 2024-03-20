package figures2;

/**
 *
 * @author Jose
 */
public class Quadrat extends Figura implements Mesurable, Escalable {
    private double costat;

    public Quadrat(String nom, double costat) {
        super(nom);
        this.costat = costat;
    }

    public double getCostat() {
        return costat;
    }

    public void setCostat(double costat) {
        this.costat = costat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quadrat{");
        sb.append("costat=").append(costat);
        sb.append('}');
        return sb.toString();
    }
    
    @Override
    public double perimetre() {
        return 4.0*costat;
    }

    @Override
    public void escalar(double factor) {
        costat *= factor;
    }
    
}
