package cloud.robinzon.backend.settings.vm.price.resources.history;

import cloud.robinzon.backend.settings.vm.price.resources.VmPriceEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class VmPriceHistoryKey
        implements Serializable {

    private VmPriceEntity vmPriceEntity;
    private Timestamp timestamp;

}
