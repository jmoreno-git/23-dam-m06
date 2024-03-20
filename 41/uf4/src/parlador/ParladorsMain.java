package parlador;

/**
 *
 * @author ProvenSoft
 */
public class ParladorsMain {

    public static void main(String[] args) {
        Parlador[] parladors = new Parlador[4];
        parladors[0] = new Gos();
        parladors[1] = new Gat();
        parladors[2] = new Ocell();
        parladors[3] = new Cucu();
        for (Parlador parlador : parladors) {
            queParli(parlador);
        }
    }
    
    private static void queParli(Parlador parlador) {
        parlador.parla();
    }
    
}
