package cloud.robinzon.backend.db.fm.resources;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
public class FmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String specifications;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean vm;

    @ManyToOne
    @JoinColumn
    private ClientEntity client;

    @Column(nullable = false)
    private boolean deleted;

    public FmEntity(Long id,
                    String name,
                    String ip,
                    String title,
                    String specifications,
                    String description,
                    int price,
                    boolean vm,
                    ClientEntity client,
                    boolean deleted) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        this.client = client;
        this.deleted = deleted;
    }

    public FmEntity(String name,
                    String ip,
                    String title,
                    String specifications,
                    String description,
                    int price,
                    boolean vm,
                    ClientEntity client) {
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        this.client = client;
    }

    public FmEntity() {
    }

    public void update(String name,
                       String ip,
                       String title,
                       String specifications,
                       String description,
                       int price,
                       boolean vm) {
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getTitle() {
        return title;
    }

    public String getSpecifications() {
        return specifications;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public boolean getVm() {
        return vm;
    }

    public ClientEntity getClient() {
        return client;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
