package cloud.robinzon.backend.security.user.resources.history;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class UserHistoryKey
        implements Serializable {

    private UserEntity userEntity;
    private Timestamp timestamp;

}