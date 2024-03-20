package appliance;

/**
 *
 * @author Provensoft
 */
public class StoreMain { 
    private Store myStore;
    
    public static void main(String[] args) {
        StoreMain ap = new StoreMain();
        ap.run();
    }
    
    public void run() {
//        Appliance[] appliances = generateData();
//        this.myStore = new Store("Botiga de Paco", appliances);
//        printAppliances(myStore.getAppliances());
//        String nameToSearch = "Radio";
//        Appliance result = myStore.searchAppliance(nameToSearch);
//        if (result != null) {
//            System.out.println(result.toString());
//        } else {
//            System.out.println("No trobat");
//        }
        //
        System.out.println("vat: "+Appliance.vat);
        Appliance ap1 = new Appliance("Tv50", "gray", 1000.0, EnergyType.F, 10.0);
        Appliance ap2 = new Appliance("Fridge", "yellow", 1300.0, EnergyType.A, 300.0);
        System.out.println("ap1: "+ap1);
        System.out.println("ap2: "+ap2);
//        ap2.vat = 0.15;
//        System.out.println("ap1: "+ap1);
//        System.out.println("ap2: "+ap2);
        System.out.println("ap1 with vat: "+ap1.getPriceWithVat());
        System.out.format("Net price %.2f. Price with VAT: %.2f%n", 
                100.0, Appliance.calcPriceWithVat(100.0));
        System.out.format("Net price %.2f. Price with VAT: %.2f%n", 
                200.0, ap1.calcPriceWithVat(200.0));
        WeekDays today = WeekDays.TUESDAY;
        System.out.println("today is "+today);
    }

    private void printAppliances(Appliance[] appliances) {
        for (Appliance elem : appliances) {
            System.out.println(elem.toString());
        }
    }
    
    private Appliance[] generateData() {
        Appliance[] devices = new Appliance[5];
        devices[0] = new Appliance("Tv", "black", 800.0, EnergyType.C, 20.0);
        devices[1] = new Appliance("Fridge", "White", 1200.0, EnergyType.B, 200.0);
        devices[2] = new Appliance("Kitchen", "Gray", 1000.0, EnergyType.F, 80.0);
        devices[3] = new Appliance("Toaster", "Blue", 30.0, EnergyType.D, 2.0);
        devices[4] = new Appliance("Washing Machine", "White", 600.0, EnergyType.C, 100.0);
        return devices;
    }
    
}
