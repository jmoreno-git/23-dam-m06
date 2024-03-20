package cat.proven.dbpersist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * generic DAO
 * loads queries from a file and uses reflection
 * @author Jose
 * @param <T> the base type of DAO
 */
public abstract class DbDao<T> {

    /**
     * queries for this object
     */
    protected Properties queries;
    /**
     * the path to the file with the queries
     */
    protected String queriesFile;
    /**
     * DbConnector object with connection to database properties
     */
    protected final DbConnector dbConnector;
    /**
     * connection to use in queries
     */
    protected Connection connection;
    
    protected List<Object> lastGeneratedKeys;
    
    public DbDao(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
        this.queries = new Properties();
        initQueries(); //possibly empty if queries are to be loaded from file (see setQueriesFile())
        this.lastGeneratedKeys = null;
    }

    public Connection getConnection() throws SQLException {
        if ((connection == null) || (!connection.isValid(0))) {
            connection = this.dbConnector.getConnection();
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getQueriesFile() {
        return queriesFile;
    }

    public void setQueriesFile(String queriesFile)
            throws FileNotFoundException, IOException {
        this.queriesFile = queriesFile;
        queries = new Properties();
        queries.load(new FileReader(queriesFile));
    }

    /**
     * gets last generated keys saved with last insert query
     * @return list of keys
     */
    public List<Object> getLastGeneratedKeys() {
        return lastGeneratedKeys;
    }

    /**
     * performs select query
     *
     * @param queryName the name of the query (in queries properties)
     * @param values a list of values to be bound to query parameters
     * @return list of T objectes retrieved by query
     * @throws java.sql.SQLException in case of error
     */
    public List<T> execQuery(String queryName, List<Object> values) throws SQLException {
        List<T> result = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty(queryName);
            PreparedStatement st = conn.prepareStatement(query);
            for (int i = 0; i < values.size(); i++) {
                st.setObject(i + 1, values.get(i));
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                T obj = fromResultSet(rs);
                if (obj != null) {
                    result.add(obj);
                }
            }
        }
        return result;
    }

    /**
     * performs update query
     *
     * @param queryName the name of the query (in queries properties)
     * @param values a list of values to be bound to query parameters
     * @return the number of rows affected by query
     * @throws java.sql.SQLException in case of error
     */
    public int execUpdate(String queryName, List<Object> values) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty(queryName);
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < values.size(); i++) {
                st.setObject(i + 1, values.get(i));
            }
            result = st.executeUpdate();
            if (result == 1) {
                ResultSet rs = st.getGeneratedKeys();
                lastGeneratedKeys = new ArrayList<>();
                while (rs.next()) {
                    lastGeneratedKeys.add(rs.getLong(1));
                }
            }
        }
        return result;
    }   
    
    /**
     * converts resultset row to T object
     *
     * @param rs the resultset obtained in last query
     * @return T object with data of current row
     * @throws SQLException in case of error
     */
    protected abstract T fromResultSet(ResultSet rs) throws SQLException;

    /**
     * loads queries (not required if queries are loaded from file in setQueriesFile()
     */
    protected abstract void initQueries();
    
    /**
     * gets a query by its name
     * @param queryName the name of the query
     * @return name of query or null if not found
     */
    public abstract String getQuery(String queryName);
    
}
