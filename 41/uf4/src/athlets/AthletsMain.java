package athlets;

import java.util.stream.IntStream;

/**
 *
 * @author alumne
 */
public class AthletsMain {
    
    private Athlet[] athlets;
    
    public static void main(String[] args) {
        AthletsMain am = new AthletsMain();
        am.run();
    }

    private void run() {
        generateAthlets();
        System.out.println("Show athlets:");
        showAthlets();
        System.out.println("Make them attend class:");
        makeAthletsAttendClass();
        System.out.println("Enroll them in competition:");
        takeCompetition();
        System.out.println("Show athlets:");
        showAthlets();
    }
    
    private void showAthlets() {
        for (Athlet athlet : athlets) {
            System.out.println(athlet);
        }
    }
    
    private void generateAthlets() {
        athlets = new Athlet[15];
        IntStream.range(0, 15)
            .forEach((value) -> {
                Athlet a = new Athlet(
                   String.format("name%02d", value+1),
                   21+value,
                   String.format("discip%02d", value+1),
                   0
                );
                athlets[value] = a;
            });
    }
    
    private void makeAthletsAttendClass() {
        for (Athlet athlet : athlets) {
            System.out.println(athlet.attendClass());
        }
    }
    
    private void takeCompetition() {
        for (int i = 0; i < athlets.length; i++) {
            athlets[i].setNumber(i+1);
        }
    }
}
