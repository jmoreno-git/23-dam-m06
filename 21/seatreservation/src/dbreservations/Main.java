package dbreservations;

import dbreservations.model.DbSeatsHelper;
import dbreservations.model.Seat;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Main {

    public static void main(String[] args) {
        DbSeatsHelper dbHelper = new DbSeatsHelper();
        String name = "Peter";
        List<Seat> seats = Arrays.asList(new Seat(1), new Seat(3), new Seat(1));
        dbHelper.book(name, seats);
    }
    
}
