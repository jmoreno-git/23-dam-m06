package dbreservations.model;

/**
 *
 * @author Jose
 */
public class ReservationSeat {

    private long reservationId;
    private long seatId;

    public ReservationSeat(long reservationId, long seatId) {
        this.reservationId = reservationId;
        this.seatId = seatId;
    }

    public ReservationSeat() {
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (int) (this.reservationId ^ (this.reservationId >>> 32));
        hash = 13 * hash + (int) (this.seatId ^ (this.seatId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationSeat other = (ReservationSeat) obj;
        if (this.reservationId != other.reservationId) {
            return false;
        }
        return this.seatId == other.seatId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReservationSeat{");
        sb.append("reservationId=").append(reservationId);
        sb.append(", seatId=").append(seatId);
        sb.append('}');
        return sb.toString();
    }

}
