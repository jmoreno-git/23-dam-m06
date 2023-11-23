package dbreservations.dal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Generic class to encapsulate properties of database connection.
 *
 * @author ProvenSoft
 */
public class DbConnect {

    private static DbConnect instance;
    
    /**
     * the path to properties file with database connection data.
     */
    private final String DBCONNPROPSFILE = "files/dbconn.properties";
    /**
     * database url
     */
    private final String dbUrl;
    /**
     * database user name
     */
    private final String user;
    /**
     * database user password
     */
    private final String password;

    private DbConnect() throws FileNotFoundException, IOException {
        Properties connProps = new Properties();
        connProps.load(new FileReader(DBCONNPROPSFILE));
        dbUrl = String.format("%s//%s/%s?%s",
                connProps.getProperty("protocol"),
                connProps.getProperty("host"),
                connProps.getProperty("dbname"),
                connProps.getProperty("dbparams"));
        user = connProps.getProperty("user");
        password = connProps.getProperty("password");
    }
    
    public static DbConnect getInstance() throws IOException {
        if (instance == null) {
            instance = new DbConnect();
        }
        return instance;
    }


    /**
     * gets a connection from database
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, user, password);
    }

}
