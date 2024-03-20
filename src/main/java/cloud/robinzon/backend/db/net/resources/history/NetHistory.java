package cloud.robinzon.backend.db.net.resources.history;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@IdClass(NetHistoryKey.class)
public class NetHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private NetEntity netEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false, length = 50)
    private String domain;

    @Column(nullable = false, length = 15)
    private String subnet;

    @Column(nullable = false, length = 15)
    private String mask;

    @Column(nullable = false, length = 15)
    private String dns1;

    @Column(nullable = false, length = 15)
    private String dns2;

    @Column(nullable = false, length = 15)
    private String dns3;

    @Column(nullable = false)
    private boolean cloud;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean deleted;

    public NetHistory(NetEntity netEntity,
                      UserEntity changeBy,
                      String domain,
                      String subnet,
                      String mask,
                      String dns1,
                      String dns2,
                      String dns3,
                      boolean cloud,
                      String title,
                      String description,
                      boolean deleted) {
        this.netEntity = netEntity;
        this.changeBy = changeBy;
        this.domain = domain;
        this.subnet = subnet;
        this.mask = mask;
        this.dns1 = dns1;
        this.dns2 = dns2;
        this.dns3 = dns3;
        this.cloud = cloud;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public NetHistory(NetEntity netEntity,
                      UserEntity changeBy) {
        this.netEntity = netEntity;
        this.changeBy = changeBy;
        this.domain = netEntity.getDomain();
        this.subnet = netEntity.getSubnet();
        this.dns1 = netEntity.getDns1();
        this.dns2 = netEntity.getDns2();
        this.dns3 = netEntity.getDns3();
        this.cloud = netEntity.isCloud();
        this.title = netEntity.getTitle();
        this.description = netEntity.getDescription();
        this.deleted = true;
    }

    public NetHistory() {
    }

    public NetEntity getNetEntity() {
        return netEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public String getDomain() {
        return domain;
    }

    public String getSubnet() {
        return subnet;
    }

    public String getMask() {
        return mask;
    }

    public String getDns1() {
        return dns1;
    }

    public String getDns2() {
        return dns2;
    }

    public String getDns3() {
        return dns3;
    }

    public boolean isCloud() {
        return cloud;
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

}