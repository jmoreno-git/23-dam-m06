package cat.proven.flightpassenger;

import cat.proven.flightpassenger.model.DataProvider;
import cat.proven.flightpassenger.model.Flight;
import cat.proven.flightpassenger.model.Passenger;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Main class for Flights and passengers application.
 * @author ProvenSoft
 */
public class Main {

    public static void main(String[] args) {
        //TODO implement presentation layer (or controllers and views)
        //TESTS
        DataProvider dbHelper = new DataProvider();
        System.out.println("> All flights");
        dbHelper.allFlights().forEach(System.out::println);
        System.out.println("> All passengers");
        dbHelper.allPassengers().forEach(System.out::println);
        System.out.println("> All flightpassengers");
        dbHelper.allFlightPassengers().forEach(System.out::println);
        System.out.println("> All passengers in flight with id=2");
        dbHelper.allPassengersInFlight(new Flight(2)).forEach(System.out::println);
        System.out.println("> All flightpassengers after attaching passenger 9 to flight 9");
        dbHelper.attachPassengerToFlight(new Flight(9), new Passenger(9));
        dbHelper.allFlightPassengers().forEach(System.out::println);
        System.out.println("> All flightpassengers after detaching passenger 9 from flight 9");
        dbHelper.detachPassengerFromFlight(new Flight(9), new Passenger(9));
        dbHelper.allFlightPassengers().forEach(System.out::println);
        System.out.println("> Try to remove flight with id=5");
        System.out.println(dbHelper.removeFlight(new Flight(5)));
        System.out.println("> All flights after removing flight with id=5");
        dbHelper.allFlights().forEach(System.out::println);
        System.out.println("> Try to remove flight with id=9");
        System.out.println(dbHelper.removeFlight(new Flight(9)));
        System.out.println("> All flights after removing flight with id=9");
        dbHelper.allFlights().forEach(System.out::println);
        System.out.println("> Try to add new flight");
        System.out.println(dbHelper.addFlight(
            new Flight(0, 
                    "code21", 
                    121, 
                    LocalDate.parse("2023-01-21"), 
                    LocalTime.parse("01:01:21"))
            )
        );
        System.out.println("> All flights after adding a new flight");
        dbHelper.allFlights().forEach(System.out::println);
        String code = "code03";
        System.out.println("> Flight with code "+code);
        dbHelper.flightWithCode(code)
            .ifPresentOrElse(
                    (t) -> {
                        System.out.println(t.toString());
                    },  
                    () -> {
                        System.out.println("No flight found with that code");
                    }
            );
    }
    
}