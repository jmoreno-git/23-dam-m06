
package cat.proven.utils.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Jose
 */
public interface DbConnectorInterface {
    /**
     * gets and returns a connection to database
     *
     * @return connection
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException;
}
