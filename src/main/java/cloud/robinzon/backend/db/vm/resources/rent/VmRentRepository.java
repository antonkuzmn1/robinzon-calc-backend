package cloud.robinzon.backend.db.vm.resources.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmRentRepository
        extends JpaRepository<VmRent, VmRentKey> {
}
