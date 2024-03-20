package cloud.robinzon.backend.security.group.resources;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Column(nullable = false)
    private boolean deleted;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnoreProperties("groups")
    private Set<UserEntity> users = new HashSet<>();

    public GroupEntity(String name,
                       String title,
                       String description) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.users = null;
        this.deleted = false;
    }

    public GroupEntity() {
    }

    public void update(String name,
                       String title,
                       String description) {
        this.name = name;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
