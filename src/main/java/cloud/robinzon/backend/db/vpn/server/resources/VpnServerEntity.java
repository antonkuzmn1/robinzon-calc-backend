package cloud.robinzon.backend.db.vpn.server.resources;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.settings.vpn.type.resources.VpnTypeEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class VpnServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonIgnoreProperties("vpnServerEntity")
    @JoinTable
    private Set<VpnTypeEntity> vpnTypeEntity = new HashSet<>();

    @UpdateTimestamp
    private Timestamp timestamp;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    public VpnServerEntity(Long id,
                           String title,
                           String description,
                           String ip,
                           String publicKey,
                           NetEntity netEntity,
                           Set<VpnTypeEntity> vpnTypeEntity,
                           boolean deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ip = ip;
        this.publicKey = publicKey;
        this.netEntity = netEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.deleted = deleted;
    }

    public VpnServerEntity(String title,
                           String description,
                           String ip,
                           String publicKey,
                           NetEntity netEntity,
                           Set<VpnTypeEntity> vpnTypeEntity) {
        this.title = title;
        this.description = description;
        this.ip = ip;
        this.publicKey = publicKey;
        this.netEntity = netEntity;
        this.vpnTypeEntity = vpnTypeEntity;
    }

    public void update(String title,
                       String description,
                       String ip,
                       String publicKey,
                       NetEntity netEntity,
                       Set<VpnTypeEntity> vpnTypeEntity) {
        this.title = title;
        this.description = description;
        this.ip = ip;
        this.publicKey = publicKey;
        this.netEntity = netEntity;
        this.vpnTypeEntity = vpnTypeEntity;
    }

}
