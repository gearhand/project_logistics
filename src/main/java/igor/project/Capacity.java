package igor.project;

import javax.persistence.*;

@Entity
@Table(name = "Capacities")
public class Capacity {

    @Id
    @ManyToOne
    private Type type;

    @Id
    @ManyToOne
    private Port port;

    @Column(nullable = false)
    private long capacity;

    public Capacity() {
    }

    public Capacity(Type type, Port port, long capacity) {
        this.type = type;
        this.port = port;
        this.capacity = capacity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }
}
