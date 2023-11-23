package dbreservations.dal;

import dbreservations.model.Reservation;
import dbreservations.model.ReservationSeat;
import dbreservations.model.Seat;
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

    private Logger logger;
    
    public DbSeatsHelper() {
        logger = Logger.getLogger("seatreservation");
    }

    public boolean book(String name, List<Seat> seats) {
        boolean success = false;
        Connection conn = null;
        try {
            //get a connection
            conn = DbConnect.getInstance().getConnection();
            //save autocommit initial state
            boolean isAutoCommit = conn.getAutoCommit();
            //disable autocommit
            conn.setAutoCommit(false);
            //save initial point on transaction
            Savepoint savePoint = conn.setSavepoint();
            //insert reservation
            logger.log(Level.INFO,"Creating reservation");
            ReservationDao rDao = new ReservationDao(conn);
            Reservation r = new Reservation(0, name);
            long reservId = rDao.insertAndGetGeneratedKey(r);
            logger.log(Level.INFO, "Reservation id: {0}", reservId);
            //insert seat reservations
            ReservationSeatDao rsDao = new ReservationSeatDao(conn);
            int seatCounter = 0;
            for (Seat seat : seats) {
                logger.log(Level.INFO, "Adding to reservation {0} seat {1}", new Object[]{reservId, seat.getId()});
                ReservationSeat rs = new ReservationSeat(reservId, seat.getId());
                logger.log(Level.INFO, "ReservationSeat: {0}", rs.toString());
                seatCounter += rsDao.insert(rs);
            }
            System.out.format("Seat counter: "+seatCounter);
            //perform commit
            conn.commit();
            logger.info("commit performed");
            //restore initial autocommit state
            conn.setAutoCommit(isAutoCommit);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            try {
                if (conn != null) {
                    logger.info("rollback performed");
                    conn.rollback();
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

}
