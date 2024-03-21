package cloud.robinzon.backend.security.group.resources.history;

import cloud.robinzon.backend.security.group.resources.GroupEntity;
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
@IdClass(GroupHistoryKey.class)
public class GroupHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private GroupEntity groupEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public GroupHistory(GroupEntity groupEntity,
                        String name,
                        String title,
                        String description,
                        UserEntity changeBy) {
        this.groupEntity = groupEntity;
        this.name = name;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        deleted = false;
    }

    public GroupHistory(GroupEntity groupEntity,
                        UserEntity changeBy) {
        this.groupEntity = groupEntity;
        this.name = groupEntity.getName();
        this.title = groupEntity.getTitle();
        this.description = groupEntity.getDescription();
        this.changeBy = changeBy;
        deleted = false;
    }

}
