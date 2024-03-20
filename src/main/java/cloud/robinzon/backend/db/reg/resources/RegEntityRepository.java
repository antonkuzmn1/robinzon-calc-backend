package cloud.robinzon.backend.db.reg.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegEntityRepository
        extends JpaRepository<RegEntity, Long> {
}
