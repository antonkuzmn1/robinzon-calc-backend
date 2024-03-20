package cloud.robinzon.backend.db.client.resources.payment;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@IdClass(ClientPaymentKey.class)
public class ClientPayment {

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity clientEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false)
    private int balance;

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    public ClientPayment(ClientEntity clientEntity,
                         int balance,
                         UserEntity changeBy) {
        this.clientEntity = clientEntity;
        this.balance = balance;
        this.changeBy = changeBy;
    }

    public ClientPayment() {
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getBalance() {
        return balance;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

}
