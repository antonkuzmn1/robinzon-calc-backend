package cloud.robinzon.backend.db.vpn.server.resources.history;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class VpnServerHistoryKey
        implements Serializable {

    private VpnServerEntity vpnServerEntity;
    private Timestamp timestamp;

}
