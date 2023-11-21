package dbreservations.dal;

import dbreservations.model.Reservation;

import java.io.FileReader;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class ReservationDao {

    private final Properties queries;
    private final String QUERY_FILE = "files/reservationqueries.properties";
    private final Connection connection;

    /**
     *
     * @param connection
     * @throws java.io.IOException
     */
    public ReservationDao(Connection connection) throws IOException {
        this.connection = connection;
        this.queries = new Properties();
        this.queries.load(new FileReader(QUERY_FILE));
    }

    public Connection getConnection() {
        return connection;
    }

    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }

    private Reservation fromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        return new Reservation(id, name);
    }

    public Reservation selectWhereId(long id) throws SQLException {
        Reservation obj = null;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectWhereId");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                obj = fromResultSet(rs);
            }
        }
        return obj;
    }

    public List<Reservation> selectAll() throws SQLException {
        List<Reservation> objList = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectAll");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Reservation s = fromResultSet(rs);
                objList.add(s);
            }
        }
        return objList;
    }

    public List<Reservation> selectLikeName(String name) throws SQLException {
        List<Reservation> objList = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectLikeName");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reservation s = fromResultSet(rs);
                objList.add(s);
            }
        }
        return objList;
    }

    public int insert(Reservation obj) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("insert");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, obj.getId());
            st.setString(2, obj.getName());
            result = st.executeUpdate();
        }
        return result;
    }

    public long insertAndGetGeneratedKey(Reservation obj) throws SQLException {
        long result = 0;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("insert");
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, obj.getId());
            st.setString(2, obj.getName());
            int count = st.executeUpdate();
            if (count == 1) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getLong(1);
                }
            }
        }
        return result;
    }

    public int update(Reservation obj) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("update");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, obj.getName());
            st.setLong(2, obj.getId());
            result = st.executeUpdate();
        }
        return result;
    }

    public int delete(Reservation entity) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("delete");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, entity.getId());
            result = st.executeUpdate();
        }
        return result;
    }
}
