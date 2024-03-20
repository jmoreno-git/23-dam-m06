package cat.proven.warehouse.model.persist;

import cat.proven.utils.exceptions.OpResult;
import cat.proven.utils.exceptions.PersistException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * encapsulates data for database connection.
 *
 * @author ProvenSoft
 */
public final class DbConnect {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String PROTOCOL = "jdbc:mysql:";
    public static final String HOST = "127.0.0.1";
    public static final String BD_NAME = "warehousedb";
    public static final String USER = "warehouseusr";
    public static final String PASSWORD = "warehousepsw";
    public static String BD_URL;
    
    public static void loadDriver() throws PersistException {
        try {
            //getConnectionProperties(); better if connection properties are read from a configuration file
            Class.forName(DRIVER);
            BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistException("Driver not found", OpResult.DB_DRIVER.getCode());
        }
    }
    
    /**
     * gets and returns a connection to database
     *
     * @return connection
     * @throws PersistException in case of connexion error
     */
    public Connection getConnection() throws PersistException {
        BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(BD_URL, USER, PASSWORD);
        } catch (SQLException ex) {
            //Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistException("Error connecting to database", OpResult.DB_NOCONN.getCode());
        }
        return conn;
    }
}