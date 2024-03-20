package cloud.robinzon.backend.db.reg.resources.history;

import cloud.robinzon.backend.db.reg.resources.RegEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
@IdClass(RegHistoryKey.class)
public class RegHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private RegEntity regEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String part;

    @Column(nullable = false, length = 50)
    private String serial;

    @Column(nullable = false)
    private Date buyDate;

    @Column(nullable = false)
    private int warrantyMonths;

    @Column(nullable = false, length = 50)
    private String provider;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public RegHistory(RegEntity regEntity,
                      String brand,
                      String name,
                      String part,
                      String serial,
                      Date buyDate,
                      int warrantyMonths,
                      String provider,
                      String title,
                      String description,
                      UserEntity changeBy,
                      boolean deleted) {
        this.regEntity = regEntity;
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.provider = provider;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    public RegHistory(RegEntity regEntity,
                      UserEntity changeBy) {
        this.regEntity = regEntity;
        this.changeBy = changeBy;
        this.brand = regEntity.getBrand();
        this.name = regEntity.getName();
        this.part = regEntity.getPart();
        this.serial = regEntity.getSerial();
        this.buyDate = regEntity.getBuyDate();
        this.warrantyMonths = regEntity.getWarrantyMonths();
        this.provider = regEntity.getProvider();
        this.title = regEntity.getTitle();
        this.description = regEntity.getDescription();
        this.deleted = true;
    }

    public RegHistory() {
    }

    public RegEntity getRegEntity() {
        return regEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getPart() {
        return part;
    }

    public String getSerial() {
        return serial;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public String getProvider() {
        return provider;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}