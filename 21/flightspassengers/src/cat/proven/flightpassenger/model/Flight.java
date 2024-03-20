
package cat.proven.flightpassenger.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * ADT for flight object
 * @author Jose
 */
public class Flight {
    private long id;
    private String code;
    private int capacity;
    private LocalDate date;
    private LocalTime time;

    public Flight(long id, String code, int capacity, LocalDate date, LocalTime time) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.date = date;
        this.time = time;
    }

    public Flight(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.code);
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
        final Flight other = (Flight) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flight{");
        sb.append("id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", capacity=").append(capacity);
        sb.append(", date=").append(date);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }

}
