/*

Copyright 2024 Anton Kuzmin (http://github.com/antonkuzmn1)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package cloud.robinzon.backend.data.fm.resources;

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

    List<FmEntity> findFmEntitiesByNameAndDeleted(String name, boolean deleted);

}
