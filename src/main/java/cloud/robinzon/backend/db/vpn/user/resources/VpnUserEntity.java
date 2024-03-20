package cloud.robinzon.backend.db.vpn.user.resources;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.settings.vpn.type.resources.VpnTypeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
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

    @Column(nullable = false)
    private boolean deleted;

    public VpnUserEntity(Long id,
                         VpnServerEntity vpnServerEntity,
                         VpnTypeEntity vpnTypeEntity,
                         String ip,
                         String username,
                         String password,
                         String fullName,
                         String title,
                         String description,
                         boolean deleted) {
        this.id = id;
        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

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

    public VpnUserEntity() {
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

    public Long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public VpnServerEntity getVpnServerEntity() {
        return vpnServerEntity;
    }

    public VpnTypeEntity getVpnTypeEntity() {
        return vpnTypeEntity;
    }

    public String getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
