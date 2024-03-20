
package figures2;

/**
 *
 * @author Jose
 */
public class FiguresTest {
    
    public static void main(String[] args) {
        //crear figures
        Quadrat q1 = new Quadrat("Quadrat1", 3.0);
        Rectangle r1 = new Rectangle("Rectangle1", 2.0, 3.0);
        System.out.println("Mostrar perímetre de cada objecte");
        System.out.println("q1:"+q1.perimetre());
        System.out.println("r1:"+r1.perimetre());
        //crear array de 'mesurables'
        Mesurable[] figs = new Mesurable[2];
        figs[0] = q1;
        figs[1] = r1;
        //mostrar dades de 'figures'
        System.out.println("Mostrar perímetre de les figures de l'array de 'mesurables'");
        for (int i = 0; i < figs.length; i++) {
            mostraInfoFigura(figs[i]);
        }
        //ús de typecast per considerar com a figura una figura mesurable
        System.out.println("((Figura)figs[0]).getNom(): "+((Figura)figs[0]).getNom());
        //crear figura genèrica
        Figura galeta = new Figura("cookie");
        System.out.println("Figura genèrica: "+galeta.toString());
        //crear array de 'escalables'
        Escalable[] figs2 = new Escalable[2];
        figs2[0] = q1;
        figs2[1] = r1; 
        //escalar figures
        System.out.println("Escalar figures");
        for (int i = 0; i < figs2.length; i++) {
            figs2[i].escalar(1.5);
        }
        //mostrar figures després de l'escalat
        System.out.println("Figures després de l'escalat");
        for (Escalable f : figs2) {
            System.out.println(f.toString());
        }
    }
    
    public static void mostraInfoFigura(Mesurable f) {
        System.out.format("La figura té perímetre %.2f\n",
                 f.perimetre());
    }
    
}
