package cat.proven.dbpersist;

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
 * @author Jose
 */
public class DbConnector {

    /**
     * the path to properties file with database connection data.
     */
    private String propsFile;
    /**
     * database url
     */
    private String dbUrl;
    /**
     * database user name
     */
    private String user;
    /**
     * database user password
     */
    private String password;

    public DbConnector() {
    }

    public String getPropsFile() {
        return propsFile;
    }

    /**
     *
     * @param filename the path to file with connection properties
     * @throws FileNotFoundException if filename not found
     * @throws IOException in case of error reading file
     */
    public void setPropsFile(String filename)
            throws FileNotFoundException, IOException {
        propsFile = filename;
        Properties connProps = new Properties();
        connProps.load(new FileReader(propsFile));
        dbUrl = String.format("%s//%s/%s?%s",
                connProps.getProperty("protocol"),
                connProps.getProperty("host"),
                connProps.getProperty("dbname"),
                connProps.getProperty("dbparams"));
        user = connProps.getProperty("user");
        password = connProps.getProperty("password");
    }

    /**
     * gets a connection to database
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, user, password);
    }

    /**
     * closes a connection to database
     *
     * @param connection the connection to close
     * @throws SQLException
     */
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
    
}