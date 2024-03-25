package cloud.robinzon.backend.db.reg.resources.history;

import cloud.robinzon.backend.db.reg.resources.RegEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.tools.HistoryTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@IdClass(RegHistoryKey.class)
public class RegHistory
    extends HistoryTemplate<RegEntity> {

    @Column(length = 100)
    private String brand;

    @NonNull
    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String part;

    @Column(length = 50)
    private String serial;

    @Column
    private Date buyDate;

    @Column
    private int warrantyMonths;

    @Column(length = 50)
    private String provider;

    public RegHistory(RegEntity entity,
                      UserEntity changeBy) {
        this.entity = entity;
        this.changeBy = changeBy;
        this.brand = entity.getBrand();
        this.name = entity.getName();
        this.part = entity.getPart();
        this.serial = entity.getSerial();
        this.buyDate = entity.getBuyDate();
        this.warrantyMonths = entity.getWarrantyMonths();
        this.provider = entity.getProvider();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.deleted = entity.isDeleted();
    }

}