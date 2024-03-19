package cloud.robinzon.backend.db.net;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class NetHistoryKey implements Serializable {

    private NetEntity netEntity;
    private Timestamp timestamp;

    public NetHistoryKey() {
    }

}
