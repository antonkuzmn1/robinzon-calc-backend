package cloud.robinzon.backend.db.reg.resources.history;

import cloud.robinzon.backend.db.reg.resources.RegEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class RegHistoryKey
        implements Serializable {

    private RegEntity regEntity;
    private Timestamp timestamp;

}