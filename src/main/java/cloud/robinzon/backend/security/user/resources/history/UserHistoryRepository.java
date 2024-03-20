package cloud.robinzon.backend.security.user.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository
        extends JpaRepository<UserHistory, UserHistoryKey> {
}
