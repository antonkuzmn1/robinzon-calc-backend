package cloud.robinzon.backend.security.group.resources.history;

import cloud.robinzon.backend.security.group.resources.GroupEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class GroupHistoryKey
        implements Serializable {

    private GroupEntity groupEntity;
    private Timestamp timestamp;

    public GroupHistoryKey() {
    }

}