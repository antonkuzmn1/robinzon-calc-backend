package cloud.robinzon.backend.tools;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@MappedSuperclass
public abstract class EntityTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @UpdateTimestamp
    protected Timestamp timestamp;

    @Setter
    @Column(nullable = false,
            columnDefinition = "boolean default false")
    protected boolean deleted;

    @Column(nullable = false, length = 50)
    protected String title;

    @Column(nullable = false)
    protected String description;

}
