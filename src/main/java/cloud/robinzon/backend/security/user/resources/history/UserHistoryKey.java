package cloud.robinzon.backend.security.user.resources.history;

import cloud.robinzon.backend.security.user.resources.UserEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class UserHistoryKey
        implements Serializable {

    private UserEntity userEntity;
    private Timestamp timestamp;

    public UserHistoryKey() {
    }

}