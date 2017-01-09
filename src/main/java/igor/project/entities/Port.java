package igor.project.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Ports")
public class Port implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, precision = 5)
    private long id;

    @Column(name = "city", unique = true, nullable = false, length = 30)
    private String city;

    @Column(name = "country", nullable = false, length = 30)
    private String country;

    public Port() {
    }

    public Port(String city, String country) {
//        this.id = id;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Port port = (Port) o;

        if (id != port.id) return false;
        if (!city.equals(port.city)) return false;
        return country.equals(port.country);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + city.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
