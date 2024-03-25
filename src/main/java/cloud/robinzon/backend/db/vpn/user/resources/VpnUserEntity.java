package cloud.robinzon.backend.db.vpn.user.resources;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class VpnUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VpnServerEntity vpnServerEntity;

    @ManyToOne
    @JoinColumn
    private VpnTypeEntity vpnTypeEntity;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    public VpnUserEntity(VpnServerEntity vpnServerEntity,
                         VpnTypeEntity vpnTypeEntity,
                         String ip,
                         String username,
                         String password,
                         String fullName,
                         String title,
                         String description) {
        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.deleted = false;
    }

    public void update(VpnServerEntity vpnServerEntity,
                       VpnTypeEntity vpnTypeEntity,
                       String ip,
                       String username,
                       String password,
                       String fullName,
                       String title,
                       String description) {
        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
    }

}
