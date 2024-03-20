package cloud.robinzon.backend.settings.vpn.type.resources;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.server.resources.history.VpnServerHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
public class VpnTypeEntity {

    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @UpdateTimestamp
    private Timestamp timestamp;

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

    public VpnTypeEntity() {
    }

    public void update(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Set<VpnServerEntity> getVpnServerEntity() {
        return vpnServerEntity;
    }

}