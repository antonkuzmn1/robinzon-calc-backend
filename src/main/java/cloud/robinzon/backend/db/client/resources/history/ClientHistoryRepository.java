package cloud.robinzon.backend.db.client.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientHistoryRepository
        extends JpaRepository<ClientHistory, ClientHistoryKey> {
}