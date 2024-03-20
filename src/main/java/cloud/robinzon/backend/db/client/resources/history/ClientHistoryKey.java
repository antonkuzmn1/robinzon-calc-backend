package cloud.robinzon.backend.db.client.resources.history;

import cloud.robinzon.backend.db.client.resources.ClientEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class ClientHistoryKey
        implements Serializable {

    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public ClientHistoryKey() {
    }

}