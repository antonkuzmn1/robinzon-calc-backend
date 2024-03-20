package cloud.robinzon.backend.settings.vm.price.resources.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface VmPriceHistoryRepository
        extends JpaRepository<VmPriceHistory, VmPriceHistoryKey> {
}
