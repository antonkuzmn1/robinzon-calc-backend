package cloud.robinzon.backend.settings.vpn.type.resources.history;

import cloud.robinzon.backend.settings.vpn.type.resources.VpnTypeEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class VpnTypeHistoryKey
        implements Serializable {

    private VpnTypeEntity vpnTypeEntity;
    private Timestamp timestamp;

}
