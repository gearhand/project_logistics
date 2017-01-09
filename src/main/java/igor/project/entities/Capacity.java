package igor.project.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Capacities")
public class Capacity implements Serializable{

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Capacity capacity = (Capacity) o;

        if (!type.equals(capacity.type)) return false;
        return port.equals(capacity.port);

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + port.hashCode();
        return result;
    }
}
