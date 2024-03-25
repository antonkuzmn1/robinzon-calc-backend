package cloud.robinzon.backend.db.vpn.type.resources;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.server.resources.history.VpnServerHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class VpnTypeEntity {

    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private final Set<VpnServerEntity> vpnServerEntity = new HashSet<>();

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private final Set<VpnServerHistory> vpnServerHistory = new HashSet<>();

    public VpnTypeEntity(String name) {
        this.name = name;
        this.deleted = false;
    }

    public void update(String name) {
        this.name = name;
    }

}