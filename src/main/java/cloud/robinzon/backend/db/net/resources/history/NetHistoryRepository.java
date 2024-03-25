package cloud.robinzon.backend.db.net.resources.history;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.tools.HistoryKeyTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetHistoryRepository
        extends JpaRepository<NetHistory, HistoryKeyTemplate<NetEntity>> {
}
