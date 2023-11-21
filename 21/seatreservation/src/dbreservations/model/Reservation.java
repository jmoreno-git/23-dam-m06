package dbreservations.model;

import java.util.Objects;

/**
 *
 * @author Jose
 */
public class Reservation {

    private long id;
    private String name;

    public Reservation(long in, String name) {
        this.id = in;
        this.name = name;
    }

    public Reservation(long in) {
        this.id = in;
    }

    public Reservation() {
    }

    public Reservation(Reservation other) {
        this.id = other.id;
        this.name = other.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.name);
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reservation{");
        sb.append("in=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
    
}
