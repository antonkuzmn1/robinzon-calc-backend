package cloud.robinzon.backend.db.reg.resources;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
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

    @Setter
    @Column(nullable = false)
    private boolean deleted;

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

}