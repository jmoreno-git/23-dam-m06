package dbreservations;

import dbreservations.dal.DbSeatsHelper;
import dbreservations.model.Seat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Jose
 */
public class Main {

    public static void main(String[] args) {
        configLogger("files/log.txt");
        DbSeatsHelper dbHelper = new DbSeatsHelper();
        String name = "Peter";
        List<Seat> seats = Arrays.asList(new Seat(1), new Seat(3), new Seat(1));
        dbHelper.book(name, seats);
    }

    private static Logger configLogger(String logfile) {
        //create and configure (if necessary) the message logger.
        Logger logger = Logger.getLogger("seatreservation");
        //set to false to avoid inheritance of parent handlers.
        logger.setUseParentHandlers(false);
        try {
            FileHandler handler;
            int limit = 1000000;
            int maxFiles = 3;
            handler = new FileHandler(logfile, limit, maxFiles, true);
            handler.setLevel(Level.ALL);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (FileNotFoundException ex) {
            logger.setUseParentHandlers(true);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logger;
    }

}
