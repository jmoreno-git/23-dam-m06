package cat.proven.flightpassenger.persist;

import cat.proven.utils.database.DbDao;

/**
 * Factory class to create DAO objects
 * @author Jose
 */
public final class DaoFactory {

    /**
     * Connection to database provider object
     */
    private static DbConnect dbConnect;

    /**
     * creates a DAO according to type
     * @param type the type or name of DAO to create
     * @return a proper DAO or null in case of error
     */
    public static DbDao createDao(String type) {
        DbDao dao;
        try {
            dbConnect = DbConnect.getInstance();
            dao = switch (type.toLowerCase()) {
                case "flight" ->
                    new FlightDao(dbConnect);
                case "passenger" ->
                    new PassengerDao(dbConnect);
                case "flightpassenger" ->
                    new FlightPassengerDao(dbConnect);
                default ->
                    null;
            };
        } catch (ClassNotFoundException ex) {
            dao = null;
            //Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dao;
    }
}
