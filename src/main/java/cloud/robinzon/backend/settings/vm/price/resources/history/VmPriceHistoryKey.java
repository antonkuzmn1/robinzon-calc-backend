package cloud.robinzon.backend.settings.vm.price.resources.history;

import cloud.robinzon.backend.settings.vm.price.resources.VmPriceEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class VmPriceHistoryKey
        implements Serializable {

    private VmPriceEntity vmPriceEntity;
    private Timestamp timestamp;

    public VmPriceHistoryKey() {
    }

}
