package cloud.robinzon.backend.db.client.resources.history;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
@IdClass(ClientHistoryKey.class)
public class ClientHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity clientEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 12)
    private String inn;

    @Column(nullable = false)
    private int discount;

    @Column
    private int contractNumber;

    @Column
    private Date contractDate;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public ClientHistory(ClientEntity clientEntity,
                         String name,
                         String inn,
                         int discount,
                         int contractNumber,
                         Date contractDate,
                         String title,
                         String description,
                         UserEntity changeBy,
                         boolean deleted) {
        this.clientEntity = clientEntity;
        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    public ClientHistory(ClientEntity clientEntity,
                         UserEntity changeBy) {
        this.clientEntity = clientEntity;
        this.name = clientEntity.getName();
        this.inn = clientEntity.getInn();
        this.discount = clientEntity.getDiscount();
        this.contractNumber = clientEntity.getContractNumber();
        this.contractDate = clientEntity.getContractDate();
        this.title = clientEntity.getTitle();
        this.description = clientEntity.getDescription();
        this.changeBy = changeBy;
        this.deleted = true;
    }

}