package igor.project;

import javax.persistence.*;

@Entity
@Table(name = "Shipments")
public class Shipment {

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
}
