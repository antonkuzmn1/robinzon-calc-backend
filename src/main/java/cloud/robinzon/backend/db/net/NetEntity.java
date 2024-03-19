package cloud.robinzon.backend.db.net;

import cloud.robinzon.backend.db.client.ClientEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
public class NetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn
    private ClientEntity client;

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

    public NetEntity(
            Long id,
            ClientEntity client,
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
        this.id = id;
        this.client = client;
        this.domain = domain;
        this.subnet = subnet;
        this.mask = mask;
        this.dns1 = dns1;
        this.dns2 = dns2;
        this.dns3 = dns3;
        this.cloud = cloud;
        this.title = title;
        this.description = description;
    }

    public NetEntity(
            ClientEntity client,
            String domain,
            String subnet,
            String mask,
            String dns1,
            String dns2,
            String dns3,
            boolean cloud,
            String title,
            String description) {
        this.client = client;
        this.domain = domain;
        this.subnet = subnet;
        this.mask = mask;
        this.dns1 = dns1;
        this.dns2 = dns2;
        this.dns3 = dns3;
        this.cloud = cloud;
        this.title = title;
        this.description = description;
    }

    public NetEntity() {
    }

    public void update(
            String domain,
            String subnet,
            String mask,
            String dns1,
            String dns2,
            String dns3,
            boolean cloud,
            String title,
            String description) {
        this.domain = domain;
        this.subnet = subnet;
        this.mask = mask;
        this.dns1 = dns1;
        this.dns2 = dns2;
        this.dns3 = dns3;
        this.cloud = cloud;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public ClientEntity getClient() {
        return client;
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

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}