
package cat.proven.flightpassenger.model;

import java.util.Objects;

/**
 * ADT for passenger object
 * @author Jose
 */
public class Passenger {
    private long id;
    private String phone;
    private String name;
    private boolean minor;

    public Passenger(long id, String phone, String name, boolean minor) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.minor = minor;
    }

    public Passenger(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMinor() {
        return minor;
    }

    public void setMinor(boolean minor) {
        this.minor = minor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.phone);
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
        final Passenger other = (Passenger) obj;
        return Objects.equals(this.phone, other.phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Passenger{");
        sb.append("id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", name=").append(name);
        sb.append(", minor=").append(minor);
        sb.append('}');
        return sb.toString();
    }
    
    
}
