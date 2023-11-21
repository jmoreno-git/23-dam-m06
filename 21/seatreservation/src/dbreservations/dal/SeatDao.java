package dbreservations.dal;

import dbreservations.model.Seat;

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
public class SeatDao {

    private final Properties queries;
    private final String QUERY_FILE = "files/seatqueries.properties";
    private final Connection connection;

    /**
     *
     * @param connection
     * @throws java.io.IOException
     */
    public SeatDao(Connection connection) throws IOException {
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

    private Seat fromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        return new Seat(id, name);
    }

    public Seat selectWhereId(long id) throws SQLException {
        Seat obj = null;
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

    public List<Seat> selectAll() throws SQLException {
        List<Seat> objList = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectAll");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Seat s = fromResultSet(rs);
                objList.add(s);
            }
        }
        return objList;
    }

    public List<Seat> selectLikeName(String name) throws SQLException {
        List<Seat> objList = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectLikeName");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Seat s = fromResultSet(rs);
                objList.add(s);
            }
        }
        return objList;
    }

    public int insert(Seat obj) throws SQLException {
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

    public int update(Seat obj) throws SQLException {
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

    public int delete(Seat entity) throws SQLException {
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
