package cloud.robinzon.backend.db.vm.resources.history;

import cloud.robinzon.backend.db.vm.resources.VmEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class VmHistoryKey
        implements Serializable {

    private VmEntity vmEntity;
    private Timestamp timestamp;

    public VmHistoryKey() {
    }

}