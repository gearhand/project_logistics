package igor.project.entities;

import javax.persistence.*;

@Entity
@Table(name="Companies")
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, precision = 5)
    private long id;

    @Column(name = "company_name", unique = true, nullable = false, length = 30)
    private String name;

    @Column(name = "Country", nullable = false, length = 30)
    private String country;

    public Company() {
    }

    public Company(String name, String country) {
//        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
