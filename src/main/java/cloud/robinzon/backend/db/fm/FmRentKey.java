package cloud.robinzon.backend.db.fm;

import cloud.robinzon.backend.db.client.ClientEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class FmRentKey implements Serializable {

    private FmEntity fmEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public FmRentKey() {
    }

}