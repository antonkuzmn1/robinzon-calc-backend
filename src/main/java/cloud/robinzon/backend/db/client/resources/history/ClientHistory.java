package cloud.robinzon.backend.db.client.resources.history;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.tools.HistoryTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@IdClass(ClientHistoryKey.class)
public class ClientHistory
        extends HistoryTemplate<ClientEntity> {

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @Size(min = 8, max = 12)
    @Column(length = 12)
    private String inn;

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private int discount;

    @Column
    private int contractNumber;

    @Column
    private Date contractDate;

    public ClientHistory(ClientEntity entity,
                         UserEntity changeBy) {
        this.entity = entity;
        this.changeBy = changeBy;
        this.name = entity.getName();
        this.inn = entity.getInn();
        this.discount = entity.getDiscount();
        this.contractNumber = entity.getContractNumber();
        this.contractDate = entity.getContractDate();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.deleted = entity.isDeleted();
    }

}