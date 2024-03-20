package cloud.robinzon.backend.security.user.resources.history;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@IdClass(UserHistoryKey.class)
public class UserHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public UserHistory(UserEntity userEntity,
                       String username,
                       String password,
                       String fullName,
                       String title,
                       String description,
                       UserEntity changeBy) {
        this.userEntity = userEntity;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public UserHistory(UserEntity userEntity,
                       UserEntity changeBy) {
        this.userEntity = userEntity;
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.fullName = userEntity.getFullName();
        this.title = userEntity.getTitle();
        this.description = userEntity.getDescription();
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public UserHistory() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

}
