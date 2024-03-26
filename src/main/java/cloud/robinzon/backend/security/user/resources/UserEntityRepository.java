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

package cloud.robinzon.backend.security.user.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * standard JPA repository implement for main entity
 *
 * @author Anton Kuzmin
 * @since 2024.03.23
 */

@Repository
public interface UserEntityRepository
        extends JpaRepository<UserEntity, Long> {

    @Query("SELECT COUNT(e) > 0 FROM UserEntity e WHERE e.username = :username AND e.deleted = false")
    boolean checkUnique(String username);

    UserEntity findUserEntityByUsername(String username);

//    UserEntity findUserEntityByUsernameAndPassword(String username, String password);

}
