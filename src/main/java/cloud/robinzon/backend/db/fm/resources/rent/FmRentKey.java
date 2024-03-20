package cloud.robinzon.backend.db.fm.resources.rent;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.fm.resources.FmEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class FmRentKey
        implements Serializable {

    private FmEntity fmEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public FmRentKey() {
    }

}