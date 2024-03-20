package cloud.robinzon.backend.security.user.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository
        extends JpaRepository<UserEntity, Long> {

    @Query("SELECT COUNT(e) > 0 FROM UserEntity e WHERE e.username = :username AND e.deleted = false")
    boolean checkUniqueUsername(String username);
}
