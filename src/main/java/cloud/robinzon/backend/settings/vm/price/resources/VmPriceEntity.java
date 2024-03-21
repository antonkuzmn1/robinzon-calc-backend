package cloud.robinzon.backend.settings.vm.price.resources;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
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

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    public VmPriceEntity(String param,
                         int cost) {
        this.param = param;
        this.cost = cost;
    }

    public void update(String param,
                       int cost) {
        this.param = param;
        this.cost = cost;
    }

}
