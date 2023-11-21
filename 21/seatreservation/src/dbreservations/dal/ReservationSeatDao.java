package dbreservations.dal;

import dbreservations.model.ReservationSeat;
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
 *
 * @author Jose
 */
public class ReservationSeatDao {

    private final Properties queries;
    private final String QUERY_FILE = "files/reservationseatqueries.properties";
    private final Connection connection;

    public ReservationSeatDao(Connection connection) throws FileNotFoundException, IOException {
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

    private ReservationSeat fromResultSet(ResultSet rs) throws SQLException {
        long reservationId = rs.getLong("reservation_id");
        long seatId = rs.getLong("seat_id");
        return new ReservationSeat(reservationId, seatId);
    }

    public List<ReservationSeat> selectWhereReservationId(long id) throws SQLException {
        List<ReservationSeat> objList = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectWhereReservationId");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ReservationSeat s = fromResultSet(rs);
                objList.add(s);
            }
        }
        return objList;
    }

    public List<ReservationSeat> selectWhereSeatId(long id) throws SQLException {
        List<ReservationSeat> objList = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectWhereSeatId");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ReservationSeat s = fromResultSet(rs);
                objList.add(s);
            }
        }
        return objList;
    }

    public List<ReservationSeat> selectAll() throws SQLException {
        List<ReservationSeat> objList = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("selectAll");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ReservationSeat s = fromResultSet(rs);
                objList.add(s);
            }
        }
        return objList;
    }

    public int insert(ReservationSeat obj) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("insert");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, obj.getReservationId());
            st.setLong(2, obj.getSeatId());
            result = st.executeUpdate();
        }
        return result;
    }

    public int delete(ReservationSeat obj) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        if (conn != null) {
            String query = queries.getProperty("delete");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, obj.getReservationId());
            st.setLong(2, obj.getSeatId());
            result = st.executeUpdate();
        }
        return result;
    }

}
