package cloud.robinzon.backend.db.fm;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class FmHistoryKey implements Serializable {

    private FmEntity fmEntity;
    private Timestamp timestamp;

    public FmHistoryKey() {
    }

}