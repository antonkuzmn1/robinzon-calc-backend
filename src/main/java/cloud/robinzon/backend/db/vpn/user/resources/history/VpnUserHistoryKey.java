package cloud.robinzon.backend.db.vpn.user.resources.history;

import cloud.robinzon.backend.db.vpn.user.resources.VpnUserEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class VpnUserHistoryKey
        implements Serializable {

    private VpnUserEntity vpnUserEntity;
    private Timestamp timestamp;

    public VpnUserHistoryKey() {
    }

}
