package cloud.robinzon.backend.security.group.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface GroupHistoryRepository
        extends JpaRepository<GroupHistory, Long> {
}
