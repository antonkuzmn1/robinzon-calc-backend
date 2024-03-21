package cloud.robinzon.backend.db.client.resources;

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
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
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

    @Column(nullable = false)
    private int balance;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    public ClientEntity(String name,
                        String inn,
                        int discount,
                        int contractNumber,
                        Date contractDate,
                        String title,
                        int balance,
                        String description) {
        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
        this.balance = balance;
    }

    public void update(String name,
                       String inn,
                       int discount,
                       int contractNumber,
                       Date contractDate,
                       String title,
                       String description) {
        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
    }

}
