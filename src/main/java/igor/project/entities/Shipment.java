package igor.project.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Shipments")
public class Shipment implements Serializable {

    @Id
    @ManyToOne
    private Company company;

    @Id
    @ManyToOne
    private Product product;

    @Id
    @ManyToOne
    private Port port;

    @Column(name = "volume")
    private long volume;

    public Shipment() {
    }

    public Shipment(Company company, Product product, Port port, long volume) {
        this.company = company;
        this.product = product;
        this.port = port;
        this.volume = volume;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipment shipment = (Shipment) o;

        if (!getCompany().equals(shipment.getCompany())) return false;
        if (!getProduct().equals(shipment.getProduct())) return false;
        return getPort().equals(shipment.getPort());

    }

    @Override
    public int hashCode() {
        int result = getCompany().hashCode();
        result = 31 * result + getProduct().hashCode();
        result = 31 * result + getPort().hashCode();
        return result;
    }
}
