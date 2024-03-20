
package cat.proven.flightpassenger.persist;

import cat.proven.flightpassenger.model.Flight;
import cat.proven.utils.database.DbConnectorInterface;
import cat.proven.utils.database.DbDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Jose
 */
public class FlightDao extends DbDao<Flight> {

    public FlightDao(DbConnectorInterface dbConnector)  {
        super(dbConnector);
        initQueries();
    }

    /**
     * initiatizes queries to database.
     */
    @Override
    protected void initQueries() {
        queries.put("sAll", "select * from flights");
        queries.put("insert", "insert into flights values (0, ?, ?, ?, ?)");
        queries.put("update", "update flights set code=?, capacity=?, date=?, time=? where id=?");
        queries.put("delete", "delete from flights where id=?");
        queries.put("sWhere", "select * from flights where %s=?");
    }    
    
    
    /**
     * converts resultset entry to entity object.
     * @param rs resultset to get data from.
     * @return object with data in current position of resultset.
     */
    @Override
    protected Flight fromResultSet(ResultSet rs) throws SQLException {
        Flight p;
        long id = rs.getLong("id");
        String code = rs.getString("code");
        int capacity = rs.getInt("capacity");
        LocalDate date = rs.getDate("date").toLocalDate();
        LocalTime time = rs.getTime("time").toLocalTime();
        p = new Flight(id, code, capacity, date, time);
        return p;
    }    
}
