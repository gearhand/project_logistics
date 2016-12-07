package igor.project;

import javax.persistence.*;

@Entity
@Table(name = "Ports")
public class Port {

    @Id
    @GeneratedValue
    @Column(name = "port_id", unique = true, nullable = false, precision = 5)
    private long id;

    @Column(name = "city", nullable = false, length = 30)
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
}
