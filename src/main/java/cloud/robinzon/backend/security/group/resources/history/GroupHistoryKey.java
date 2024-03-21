package cloud.robinzon.backend.security.group.resources.history;

import cloud.robinzon.backend.security.group.resources.GroupEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class GroupHistoryKey
        implements Serializable {

    private GroupEntity groupEntity;
    private Timestamp timestamp;

}