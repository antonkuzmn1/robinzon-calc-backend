package cloud.robinzon.backend.security.group.resources;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
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

    @Setter
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

    public void update(String name,
                       String title,
                       String description) {
        this.name = name;
        this.title = title;
        this.description = description;
    }

}
