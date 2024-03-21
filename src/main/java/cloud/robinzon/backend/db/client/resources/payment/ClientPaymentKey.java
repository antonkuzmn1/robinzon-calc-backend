package cloud.robinzon.backend.db.client.resources.payment;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class ClientPaymentKey
        implements Serializable {

    private ClientEntity clientEntity;
    private Timestamp timestamp;

}
