package cloud.robinzon.backend.db.vm.resources.price;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class VmPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    private UserEntity changeBy;

    @Min(0)
    @Max(999)
    @Column(nullable = false)
    private int cpu;

    @Min(0)
    @Max(999)
    @Column(nullable = false)
    private int ram;

    @Min(0)
    @Max(99)
    @Column(nullable = false)
    private int ssd;

    @Min(0)
    @Max(99)
    @Column(nullable = false)
    private int hdd;

    @SuppressWarnings("unused")
    public VmPrice(UserEntity changeBy,
                   int cpu,
                   int ram,
                   int ssd,
                   int hdd) {
        this.changeBy = changeBy;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
    }

}
