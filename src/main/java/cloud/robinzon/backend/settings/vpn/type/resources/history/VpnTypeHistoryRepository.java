package cloud.robinzon.backend.settings.vpn.type.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VpnTypeHistoryRepository
        extends JpaRepository<VpnTypeHistory, VpnTypeHistoryKey> {
}
