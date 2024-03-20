package cloud.robinzon.backend.db.vpn.user.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface VpnUserHistoryRepository
        extends JpaRepository<VpnUserHistory, VpnUserHistoryKey> {
}
