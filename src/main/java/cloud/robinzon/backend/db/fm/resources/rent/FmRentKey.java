package cloud.robinzon.backend.db.fm.resources.rent;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.fm.resources.FmEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class FmRentKey
        implements Serializable {

    private FmEntity fmEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

}