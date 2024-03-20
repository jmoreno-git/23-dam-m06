package cat.proven.flightpassenger.persist;

import cat.proven.utils.database.DbConnectorInterface;
import java.sql.*;

/**
 * encapsulates data for database connection.
 *
 * @author Jose
 */
public final class DbConnect implements DbConnectorInterface {

    private static DbConnect instance;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PROTOCOL = "jdbc:mysql:";
    private static final String HOST = "127.0.0.1";
    private static final String BD_NAME = "flightsdb";
    private static final String USER = "flightssusr";
    private static final String PASSWORD = "flightspwd";
    private static final String PARAMS = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private DbConnect() throws ClassNotFoundException {
        loadDriver();
    }

    public static DbConnect getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DbConnect();
        }
        return instance;
    }

    public void loadDriver() throws ClassNotFoundException {
        Class.forName(DRIVER);
    }

    @Override
    public Connection getConnection() throws SQLException {
        final String BD_URL = String.format("%s//%s/%s?%s", PROTOCOL, HOST, BD_NAME, PARAMS);
        Connection conn;
        conn = DriverManager.getConnection(BD_URL, USER, PASSWORD);
        return conn;
    }
}
