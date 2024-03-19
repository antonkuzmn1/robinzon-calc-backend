package cloud.robinzon.backend.db.net;

import cloud.robinzon.backend.db.client.ClientEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class NetRentKey implements Serializable {

    private NetEntity netEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public NetRentKey() {
    }

}
