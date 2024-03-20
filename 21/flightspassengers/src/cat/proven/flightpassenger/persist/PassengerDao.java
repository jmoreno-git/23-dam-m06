
package cat.proven.flightpassenger.persist;

import cat.proven.flightpassenger.model.Passenger;
import cat.proven.utils.database.DbConnectorInterface;
import cat.proven.utils.database.DbDao;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Jose
 */
public class PassengerDao extends DbDao<Passenger> {
    public PassengerDao(DbConnectorInterface dbConnector)  {
        super(dbConnector);
        initQueries();
    }

    /**
     * initiatizes queries to database.
     */
    @Override
    protected void initQueries() {
        queries.put("sAll", "select * from passengers");
        queries.put("insert", "insert into passengers values (0, ?, ?, ?, ?)");
        queries.put("update", "update passengers set code=?, capacity=?, origin=?, destination=? where id=?");
        queries.put("delete", "delete from passengers where id=?");
        queries.put("sWhere", "select * from passengers where %s=?");
    }    
    
    
    /**
     * converts resultset entry to entity object.
     * @param rs resultset to get data from.
     * @return object with data in current position of resultset.
     */
    @Override
    protected Passenger fromResultSet(ResultSet rs) throws SQLException {
        Passenger p;
        long id = rs.getLong("id");
        String phone = rs.getString("phone");
        String name = rs.getString("name");
        boolean minor = rs.getBoolean("minor");
        p = new Passenger(id, phone, name, minor);
        return p;
    }       
}
