package cloud.robinzon.backend.db.vm.resources.rent;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.vm.resources.VmEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
@IdClass(VmRentKey.class)
public class VmRent {

    @Id
    @ManyToOne
    @JoinColumn
    private VmEntity vmEntity;

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity clientEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    public VmRent(
            VmEntity vmEntity,
            ClientEntity clientEntity,
            UserEntity changeBy) {
        this.vmEntity = vmEntity;
        this.clientEntity = clientEntity;
        this.changeBy = changeBy;
    }

}