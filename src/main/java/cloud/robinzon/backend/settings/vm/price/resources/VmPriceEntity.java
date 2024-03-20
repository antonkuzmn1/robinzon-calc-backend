package cloud.robinzon.backend.settings.vm.price.resources;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@SuppressWarnings("unused")
@Entity
public class VmPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String param;

    @Column(nullable = false)
    private int cost;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Column(nullable = false)
    private boolean deleted;

    public VmPriceEntity(String param,
                         int cost) {
        this.param = param;
        this.cost = cost;
    }

    public VmPriceEntity() {
    }

    public void update(String param,
                       int cost) {
        this.param = param;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getParam() {
        return param;
    }

    public int getCost() {
        return cost;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
