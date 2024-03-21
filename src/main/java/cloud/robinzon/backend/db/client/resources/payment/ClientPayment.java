package cloud.robinzon.backend.db.client.resources.payment;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
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

}
