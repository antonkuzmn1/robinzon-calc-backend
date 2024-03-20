package cloud.robinzon.backend.settings.vm.price.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface VmPriceEntityRepository
        extends JpaRepository<VmPriceEntity, Long> {

    @Query("SELECT COUNT(e) > 0 FROM VmPriceEntity e WHERE e.param = :param AND e.deleted = false")
    boolean checkUniqueParam(String param);

}
