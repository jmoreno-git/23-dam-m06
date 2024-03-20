
package cat.proven.flightpassenger.persist;

import cat.proven.flightpassenger.model.FlightPassenger;
import cat.proven.utils.database.DbConnectorInterface;
import cat.proven.utils.database.DbDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jose
 */
public class FlightPassengerDao extends DbDao<FlightPassenger> {

    public FlightPassengerDao(DbConnectorInterface dbConnector)  {
        super(dbConnector);
        initQueries();
    }

    /**
     * initiatizes queries to database.
     */
    @Override
    protected void initQueries() {
        queries.put("sAll", "select * from flightspassengers");
        queries.put("insert", "insert into flightspassengers values (?, ?)");
        queries.put("update", "update flightspassengers set flight_id=?, passenger_id=? where id=? where flight_id=? and passenger_id=?");
        queries.put("delete", "delete from flightspassengers where flight_id=? and passenger_id=?");
        queries.put("sWhere", "select * from flightspassengers where %s=?");
    }    
    
    
    /**
     * converts resultset entry to entity object.
     * @param rs resultset to get data from.
     * @return object with data in current position of resultset.
     */
    @Override
    protected FlightPassenger fromResultSet(ResultSet rs) throws SQLException {
        FlightPassenger p;
        long flightId = rs.getLong("flight_id");
        long passengerId = rs.getLong("passenger_id");
        p = new FlightPassenger(flightId, passengerId);
        return p;
    }    
}
