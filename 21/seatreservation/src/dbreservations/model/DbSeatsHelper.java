package dbreservations.model;

import dbreservations.dal.DbConnect;
import dbreservations.dal.ReservationDao;
import dbreservations.dal.ReservationSeatDao;
import java.io.IOException;
import java.util.List;

import java.sql.Connection;
import java.sql.Savepoint;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class DbSeatsHelper {

    public DbSeatsHelper() {
        
    }

    public boolean book(String name, List<Seat> seats) {
        boolean success = false;
        Connection conn = null;
        try {
            conn = DbConnect.getInstance().getConnection();
            boolean isAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            Savepoint savePoint = conn.setSavepoint();
            //insert reservation
            Logger.getAnonymousLogger().log(Level.INFO,"Creating reservation");
            ReservationDao rDao = new ReservationDao(conn);
            Reservation r = new Reservation(0, name);
            long reservId = rDao.insertAndGetGeneratedKey(r);
            Logger.getAnonymousLogger().log(Level.INFO, "Reservation id: {0}", reservId);
            //insert seat reservations
            ReservationSeatDao rsDao = new ReservationSeatDao(conn);
            int seatCounter = 0;
            for (Seat seat : seats) {
                Logger.getAnonymousLogger().log(Level.INFO, "Adding to reservation {0} seat {1}", new Object[]{reservId, seat.getId()});
                ReservationSeat rs = new ReservationSeat(reservId, seat.getId());
                Logger.getAnonymousLogger().log(Level.INFO, "ReservationSeat: {0}", rs.toString());
                seatCounter += rsDao.insert(rs);
            }
            System.out.format("Seat counter: "+seatCounter);
            //
            conn.commit();
            Logger.getAnonymousLogger().info("commit performed");
            //
            conn.setAutoCommit(isAutoCommit);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            try {
                if (conn != null) {
                    Logger.getAnonymousLogger().info("rollback performed");
                    conn.rollback();
                }

            } catch (SQLException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

}
