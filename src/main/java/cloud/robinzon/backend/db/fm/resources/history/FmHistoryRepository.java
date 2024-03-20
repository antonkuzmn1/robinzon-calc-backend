package cloud.robinzon.backend.db.fm.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FmHistoryRepository
        extends JpaRepository<FmHistory, FmHistoryKey> {
}
