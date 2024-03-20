package cloud.robinzon.backend.security.group.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupEntityRepository
        extends JpaRepository<GroupEntity, Long> {

    @Query("SELECT COUNT(e) > 0 FROM GroupEntity e WHERE e.name = :name AND e.deleted = false")
    boolean checkUniqueName(String name);

}
