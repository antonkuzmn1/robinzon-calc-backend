package cloud.robinzon.backend.tools;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@MappedSuperclass
public abstract class HistoryTemplate<T> {

    @Id
    @ManyToOne
    @JoinColumn
    protected T entity;

    @Id
    @CreationTimestamp
    protected Timestamp timestamp;

    @ManyToOne
    @JoinColumn
    protected UserEntity changeBy;

    @Setter
    @Column(nullable = false,
            columnDefinition = "boolean default false")
    protected boolean deleted;

    @Column(nullable = false, length = 50)
    protected String title;

    @Column(nullable = false)
    protected String description;

}
