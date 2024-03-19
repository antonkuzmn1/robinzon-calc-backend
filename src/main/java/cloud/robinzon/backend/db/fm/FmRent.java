package cloud.robinzon.backend.db.fm;

import cloud.robinzon.backend.db.client.ClientEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@IdClass(FmRentKey.class)
public class FmRent {

    @Id
    @ManyToOne
    @JoinColumn
    private FmEntity fmEntity;

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity clientEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    public FmRent(FmEntity fmEntity,ClientEntity clientEntity,UserEntity changeBy) {
        this.fmEntity = fmEntity;
        this.clientEntity = clientEntity;
        this.changeBy = changeBy;
    }

    public FmRent() {
    }

    public FmEntity getFmEntity() {
        return fmEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

}