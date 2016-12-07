package igor.project;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "Product_id", unique = true, nullable = false, precision = 5)
    private long id;

    @Column(name = "product_name", nullable = false, length = 30)
    private String name;

//    @Column(name = "Type", nullable = false, precision = 5)
//    private long type_id;

    @ManyToOne
    @JoinColumn(name = "Type", foreignKey = @ForeignKey(name = "TYPE_ID_FK"))
    private Type type;

    public Product() {
    }

    public Product(String name, Type type) {
        this.name = name;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
