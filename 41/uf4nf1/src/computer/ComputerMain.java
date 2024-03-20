
package computer;

/**
 *
 * @author ProvenSoft
 */
public class ComputerMain {

    public static void main(String[] args) {
        Computer c1 = new Computer("i7", 1000, "LCD VGA", 2000);
        System.out.println("c1: "+c1.toString());
        boolean result;
        result = c1.start();
        System.out.println("result:"+result);
        System.out.println("c1: "+c1.toString());
        result = c1.activeScreenSaver();
        System.out.println("result:"+result);
        result = c1.executeProgram("MsExcel");
        System.out.println("result:"+result);
    }
    
}
