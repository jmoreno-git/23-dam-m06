package cat.proven.flightpassenger.model;

import cat.proven.flightpassenger.persist.DaoFactory;
import cat.proven.flightpassenger.persist.FlightDao;
import cat.proven.flightpassenger.persist.FlightPassengerDao;
import cat.proven.flightpassenger.persist.PassengerDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Data provider for flights and passenger application. Similar to repository
 * pattern. (model)
 *
 * @author Jose
 */
public class DataProvider {
    
    final String logfile = "files/log";

    public DataProvider() {
        configLogger();
    }

    private Logger configLogger() {
        //file path to log.
        //logfile = "files/log.txt";
        //create and configure (if necessary) the message logger.
        Logger logger = Logger.getLogger(this.getClass().getName());
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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return logger;
    }

    public List<Flight> allFlights() {
        List<Flight> data;
        try {
            FlightDao dao = (FlightDao) DaoFactory.createDao("flight");
            data = dao.execQuery("sAll", Arrays.asList());
        } catch (SQLException ex) {
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getSQLState());
            data = null;
        }
        return data;
    }

    public List<Passenger> allPassengers() {
        List<Passenger> data;
        try {
            PassengerDao dao = (PassengerDao) DaoFactory.createDao("passenger");
            data = dao.execQuery("sAll", Arrays.asList());
        } catch (SQLException ex) {
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
            data = null;
        }
        return data;
    }

    public List<FlightPassenger> allFlightPassengers() {
        List<FlightPassenger> data;
        try {
            FlightPassengerDao dao = (FlightPassengerDao) DaoFactory.createDao("flightpassenger");
            data = dao.execQuery("sAll", Arrays.asList());
        } catch (SQLException ex) {
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getSQLState());
            data = null;
        }
        return data;
    }

    public List<Passenger> allPassengersInFlight(Flight flight) {
        List<Passenger> data = new ArrayList<>();
        try {
            FlightPassengerDao dao = (FlightPassengerDao) DaoFactory.createDao("flightpassenger");
            List<FlightPassenger> fpList = dao.execWhereQuery("sWhere", "flight_id", flight.getId());
            PassengerDao daoP = (PassengerDao) DaoFactory.createDao("passenger");
            for (FlightPassenger fp : fpList) {
                Passenger p = daoP.execWhereQuery("sWhere", "id", fp.getPassengerId()).get(0);
                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getSQLState());
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
            data = null;
        }
        return data;
    }

    public boolean attachPassengerToFlight(Flight flight, Passenger passenger) {
        boolean success;
        FlightPassengerDao dao = (FlightPassengerDao) DaoFactory.createDao("flightpassenger");
        try {
            int numRows = dao.execUpdate(
                    "insert",
                    Arrays.asList(flight.getId(), passenger.getId())
            );
            success = (numRows == 1);
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getSQLState());
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }

    public boolean detachPassengerFromFlight(Flight flight, Passenger passenger) {
        boolean success;
        FlightPassengerDao dao = (FlightPassengerDao) DaoFactory.createDao("flightpassenger");
        try {
            int numRows = dao.execUpdate(
                    "delete",
                    Arrays.asList(flight.getId(), passenger.getId())
            );
            success = (numRows == 1);
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getSQLState());
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }

    public boolean addFlight(Flight flight) {
        boolean success;
        FlightDao dao = (FlightDao) DaoFactory.createDao("flight");
        try {
            int numRows = dao.execUpdate(
                    "insert",
                    Arrays.asList(
                            flight.getCode(),
                            flight.getCapacity(),
                            flight.getDate(),
                            flight.getTime()
                    )
            );
            success = (numRows == 1);
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getSQLState());
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }

    public boolean removeFlight(Flight flight) {
        boolean success;
        FlightDao dao = (FlightDao) DaoFactory.createDao("flight");
        try {
            int numRows = dao.execUpdate("delete", Arrays.asList(flight.getId()));
            success = (numRows == 1);
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getSQLState());
            //Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
    
    public Optional<Flight> flightWithCode(String code) {
        FlightDao dao = (FlightDao) DaoFactory.createDao("flight");
        Optional<Flight> result = Optional.ofNullable(null);
        try {
            List<Flight> fList = dao.execWhereQuery("sWhere", "code", code);
            if (!fList.isEmpty()) {
                result = Optional.of(fList.get(0));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
