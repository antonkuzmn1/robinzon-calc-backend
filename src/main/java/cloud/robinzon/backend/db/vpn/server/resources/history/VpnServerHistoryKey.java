package cloud.robinzon.backend.db.vpn.server.resources.history;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class VpnServerHistoryKey
        implements Serializable {

    private VpnServerEntity vpnServerEntity;
    private Timestamp timestamp;

    public VpnServerHistoryKey() {
    }

}
