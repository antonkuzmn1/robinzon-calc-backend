package cloud.robinzon.backend.db.reg.resources;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
public class RegEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
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

    @Column(nullable = false)
    private boolean deleted;

    public RegEntity(Long id,
                     String brand,
                     String name,
                     String part,
                     String serial,
                     Date buyDate,
                     int warrantyMonths,
                     String provider,
                     String title,
                     String description,
                     boolean deleted) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.provider = provider;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public RegEntity(String brand,
                     String name,
                     String part,
                     String serial,
                     Date buyDate,
                     int warrantyMonths,
                     String title,
                     String description,
                     String provider) {
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.title = title;
        this.description = description;
        this.provider = provider;
    }

    public RegEntity() {
    }

    public void update(String brand,
                       String name,
                       String part,
                       String serial,
                       Date buyDate,
                       int warrantyMonths,
                       String title,
                       String description,
                       String provider) {
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.title = title;
        this.description = description;
        this.provider = provider;
    }

    public Long getId() {
        return id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}