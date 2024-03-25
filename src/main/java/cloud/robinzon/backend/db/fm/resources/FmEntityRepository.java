package cloud.robinzon.backend.db.fm.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FmEntityRepository
        extends JpaRepository<FmEntity, Long> {

    @SuppressWarnings("unused")
    List<FmEntity> findByVm(Boolean vm);

    @Query("SELECT COUNT(f) > 0 FROM FmEntity f WHERE f.ip = :ip AND f.deleted = false")
    boolean checkUnique(String ip);

}
