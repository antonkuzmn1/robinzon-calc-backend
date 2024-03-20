package cloud.robinzon.backend.db.net.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetHistoryRepository
        extends JpaRepository<NetHistory, NetHistoryKey> {
}
