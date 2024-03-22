package cloud.robinzon.backend.security.group.resources;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class GroupEntity
implements GrantedAuthority {

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

    public GroupEntity(String name,
                       String title,
                       String description) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.deleted = false;
    }

    public void update(String name,
                       String title,
                       String description) {
        this.name = name;
        this.title = title;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

}
