package cloud.robinzon.backend.settings.vpn.type.resources.history;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.settings.vpn.type.resources.VpnTypeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
@IdClass(VpnTypeHistoryKey.class)
public class VpnTypeHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnTypeEntity vpnTypeEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public VpnTypeHistory(VpnTypeEntity vpnTypeEntity,
                          String name,
                          UserEntity changeBy) {
        this.vpnTypeEntity = vpnTypeEntity;
        this.name = name;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public VpnTypeHistory(VpnTypeEntity vpnTypeEntity,
                          UserEntity changeBy) {
        this.vpnTypeEntity = vpnTypeEntity;
        this.name = vpnTypeEntity.getName();
        this.changeBy = changeBy;
        this.deleted = true;
    }

}
