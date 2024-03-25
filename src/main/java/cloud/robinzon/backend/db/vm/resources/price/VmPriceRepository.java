package cloud.robinzon.backend.db.vm.resources.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface VmPriceRepository
        extends JpaRepository<VmPrice, Timestamp> {
}
