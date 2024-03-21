package cloud.robinzon.backend.db.net.resources.rent;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.net.resources.NetEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class NetRentKey
        implements Serializable {

    private NetEntity netEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

}
