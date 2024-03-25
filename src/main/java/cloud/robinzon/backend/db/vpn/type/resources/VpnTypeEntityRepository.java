package cloud.robinzon.backend.db.vpn.type.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VpnTypeEntityRepository
        extends JpaRepository<VpnTypeEntity, Long> {

    @Query("SELECT COUNT(e) > 0 FROM VpnTypeEntity e WHERE e.name = :name AND e.deleted = false")
    boolean checkUniqueName(String name);

}
