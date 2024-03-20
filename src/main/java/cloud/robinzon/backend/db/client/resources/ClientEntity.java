package cloud.robinzon.backend.db.client.resources;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
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

    @Column(nullable = false)
    private boolean deleted;

    public ClientEntity(Long id,
                        String name,
                        String inn,
                        int discount,
                        int contractNumber,
                        Date contractDate,
                        String title,
                        String description,
                        int balance,
                        boolean deleted) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.deleted = deleted;
    }

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

    public ClientEntity() {
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

    public Long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public int getDiscount() {
        return discount;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public Date getContractDate() {
        return contractDate;
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

    public int getBalance() {
        return balance;
    }

}
