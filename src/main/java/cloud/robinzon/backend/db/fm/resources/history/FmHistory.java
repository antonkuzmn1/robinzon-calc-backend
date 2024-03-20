package cloud.robinzon.backend.db.fm.resources.history;

import cloud.robinzon.backend.db.fm.resources.FmEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@IdClass(FmHistoryKey.class)
public class FmHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private FmEntity fmEntity;

    @Id
    @CreationTimestamp
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
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public FmHistory(FmEntity fmEntity,
                     String name,
                     String ip,
                     String title,
                     String specifications,
                     String description,
                     int price,
                     boolean vm,
                     UserEntity changeBy,
                     boolean deleted) {
        this.fmEntity = fmEntity;
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    public FmHistory(FmEntity fmEntity,
                     UserEntity changeBy) {
        this.fmEntity = fmEntity;
        this.name = fmEntity.getName();
        this.ip = fmEntity.getIp();
        this.title = fmEntity.getTitle();
        this.specifications = fmEntity.getSpecifications();
        this.description = fmEntity.getDescription();
        this.price = fmEntity.getPrice();
        this.vm = fmEntity.getVm();
        this.changeBy = changeBy;
        this.deleted = true;
    }

    public FmHistory() {
    }

    public FmEntity getFmEntity() {
        return fmEntity;
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

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

}