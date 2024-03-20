package cloud.robinzon.backend.db.vpn.server.resources.history;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.settings.vpn.type.resources.VpnTypeEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@IdClass(VpnServerHistoryKey.class)
public class VpnServerHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnServerEntity vpnServerEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String publicKey;

    @ManyToOne
    @JoinColumn(nullable = false)
    private NetEntity netEntity;

    @ManyToMany
    @JsonIgnoreProperties("vpnServerHistory")
    @JoinTable
    private Set<VpnTypeEntity> vpnTypeEntity = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public VpnServerHistory(VpnServerEntity vpnServerEntity,
                            String title,
                            String description,
                            String ip,
                            String publicKey,
                            NetEntity netEntity,
                            Set<VpnTypeEntity> vpnTypeEntity,
                            UserEntity changeBy) {
        this.vpnServerEntity = vpnServerEntity;
        this.title = title;
        this.description = description;
        this.ip = ip;
        this.publicKey = publicKey;
        this.netEntity = netEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public VpnServerHistory(VpnServerEntity vpnServerEntity,
                            UserEntity changeBy) {
        this.vpnServerEntity = vpnServerEntity;
        this.changeBy = changeBy;
        this.title = vpnServerEntity.getTitle();
        this.description = vpnServerEntity.getDescription();
        this.ip = vpnServerEntity.getIp();
        this.publicKey = vpnServerEntity.getPublicKey();
        this.netEntity = vpnServerEntity.getNetEntity();
        this.vpnTypeEntity = vpnServerEntity.getVpnTypeEntity();
        this.deleted = true;
    }

    public VpnServerHistory() {
    }

    public VpnServerEntity getVpnServerEntity() {
        return vpnServerEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIp() {
        return ip;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public NetEntity getNetEntity() {
        return netEntity;
    }

    public Set<VpnTypeEntity> getVpnTypeEntity() {
        return vpnTypeEntity;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

}