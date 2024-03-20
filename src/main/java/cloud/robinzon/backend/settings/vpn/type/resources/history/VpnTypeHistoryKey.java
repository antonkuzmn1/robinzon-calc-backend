package cloud.robinzon.backend.settings.vpn.type.resources.history;

import cloud.robinzon.backend.settings.vpn.type.resources.VpnTypeEntity;

import java.io.Serializable;
import java.security.Timestamp;

@SuppressWarnings("unused")
public class VpnTypeHistoryKey
        implements Serializable {

    private VpnTypeEntity vpnTypeEntity;
    private Timestamp timestamp;

    public VpnTypeHistoryKey() {
    }

}
