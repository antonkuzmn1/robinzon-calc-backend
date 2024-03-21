package cloud.robinzon.backend.settings.vm.price.resources.history;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.settings.vm.price.resources.VmPriceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
@IdClass(VmPriceHistoryKey.class)
public class VmPriceHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VmPriceEntity vmPriceEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 20)
    private String param;

    @Column(nullable = false)
    private int cost;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public VmPriceHistory(VmPriceEntity vmPriceEntity,
                          String param,
                          int cost,
                          UserEntity changeBy) {
        this.vmPriceEntity = vmPriceEntity;
        this.param = param;
        this.cost = cost;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public VmPriceHistory(VmPriceEntity vmPriceEntity,
                          UserEntity changeBy) {
        this.vmPriceEntity = vmPriceEntity;
        this.param = vmPriceEntity.getParam();
        this.cost = vmPriceEntity.getCost();
        this.changeBy = changeBy;
        this.deleted = true;
    }

}
