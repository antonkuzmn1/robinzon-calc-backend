package cloud.robinzon.backend.db.reg;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class RegHistoryKey implements Serializable {

    private RegEntity regEntity;
    private Timestamp timestamp;

    public RegHistoryKey() {
    }

}