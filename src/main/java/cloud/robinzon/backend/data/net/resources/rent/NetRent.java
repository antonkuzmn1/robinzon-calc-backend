/*
package cloud.robinzon.backend.db.net.resources.rent;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.net.resources.NetEntity;
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
@IdClass(NetRentKey.class)
public class NetRent {

    @Id
    @ManyToOne
    @JoinColumn
    private NetEntity netEntity;

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

    public NetRent(NetEntity netEntity,
                   ClientEntity clientEntity,
                   UserEntity changeBy) {
        this.netEntity = netEntity;
        this.clientEntity = clientEntity;
        this.changeBy = changeBy;
    }

}
*/
