package cloud.robinzon.backend.db.vm.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmHistoryRepository
        extends JpaRepository<VmHistory, VmHistoryKey> {
}
