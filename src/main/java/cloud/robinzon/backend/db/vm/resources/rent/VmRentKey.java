package cloud.robinzon.backend.db.vm.resources.rent;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.vm.resources.VmEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class VmRentKey implements Serializable {

    private VmEntity vmEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public VmRentKey() {
    }

}