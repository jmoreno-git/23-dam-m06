
package cat.proven.flightpassenger.model;

/**
 * ADT for flightpassenger object (the one who has both id)
 * @author Jose
 */
public class FlightPassenger {
    private long flightId;
    private long passengerId;

    public FlightPassenger(long flightId, long passengerId) {
        this.flightId = flightId;
        this.passengerId = passengerId;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.flightId ^ (this.flightId >>> 32));
        hash = 97 * hash + (int) (this.passengerId ^ (this.passengerId >>> 32));
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
        final FlightPassenger other = (FlightPassenger) obj;
        if (this.flightId != other.flightId) {
            return false;
        }
        return this.passengerId == other.passengerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FlightPassenger{");
        sb.append("flightId=").append(flightId);
        sb.append(", passengerId=").append(passengerId);
        sb.append('}');
        return sb.toString();
    }

}
