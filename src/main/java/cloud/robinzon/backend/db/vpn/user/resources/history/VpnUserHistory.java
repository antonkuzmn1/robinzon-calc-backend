package cloud.robinzon.backend.db.vpn.user.resources.history;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.user.resources.VpnUserEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import cloud.robinzon.backend.settings.vpn.type.VpnTypeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@IdClass(VpnUserHistoryKey.class)
public class VpnUserHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnUserEntity vpnUserEntity;

    @Id
    @CreationTimestamp
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

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    public VpnUserHistory(VpnUserEntity vpnUserEntity,
                          VpnServerEntity vpnServerEntity,
                          VpnTypeEntity vpnTypeEntity,
                          String ip,
                          String username,
                          String password,
                          String fullName,
                          String title,
                          String description,
                          UserEntity changeBy) {
        this.vpnUserEntity = vpnUserEntity;
        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public VpnUserHistory(VpnUserEntity vpnUserEntity,
                          UserEntity changeBy) {
        this.vpnUserEntity = vpnUserEntity;
        this.vpnServerEntity = vpnUserEntity.getVpnServerEntity();
        this.vpnTypeEntity = vpnUserEntity.getVpnTypeEntity();
        this.ip = vpnUserEntity.getIp();
        this.username = vpnUserEntity.getUsername();
        this.password = vpnUserEntity.getPassword();
        this.fullName = vpnUserEntity.getFullName();
        this.title = vpnUserEntity.getTitle();
        this.description = vpnUserEntity.getDescription();
        this.changeBy = changeBy;
        this.deleted = true;
    }

    public VpnUserHistory() {
    }

    public VpnUserEntity getVpnUserEntity() {
        return vpnUserEntity;
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

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
