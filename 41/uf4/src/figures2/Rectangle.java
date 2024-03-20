package figures2;

/**
 *
 * @author Jose
 */
public class Rectangle extends Figura implements Mesurable, Escalable {
    private double base;
    private double altura;

    public Rectangle(String nom, double base, double altura) {
        super(nom);
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rectangle{");
        sb.append("base=").append(base);
        sb.append(", altura=").append(altura);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public double perimetre() {
        return 2.0*(base+altura);
    }

    @Override
    public void escalar(double factor) {
        base *= factor;
        altura *= factor;
    }
    
}
