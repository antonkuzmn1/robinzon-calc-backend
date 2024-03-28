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

package cloud.robinzon.backend.security.user;

import cloud.robinzon.backend.security.tools.JwtUtilStatic;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.security.user.resources.UserEntityRepository;
import cloud.robinzon.backend.security.user.resources.history.UserHistory;
import cloud.robinzon.backend.security.user.resources.history.UserHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static cloud.robinzon.backend.common.Log.*;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class manages entities in the database.
 * </p>
 * <p>
 * It provides the following methods:
 * </p>
 * <ul>
 *   <li>{@code insert} - Inserts a new entity into the database.</li>
 *   <li>{@code update} - Updates an existing entity.</li>
 *   <li>{@code delete} - Deletes an entity.</li>
 * </ul>
 * <p>
 * Refer to the respective method documentation for more details.
 * </p>
 *
 * @author Anton Kuzmin
 * @version 2024.03.27
 * @since 2024.03.26
 */

@Service
@AllArgsConstructor
public class UserEntityManager {

    private final UserEntityRepository entityRepository;
    private final UserHistoryRepository historyRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Returns a ResponseEntity with status code 200 (OK) and the updated NetEntity as the response body.
     * Logs the new values of the entity, saves the entity to the repository, saves a new entry in the history repository,
     * and logs the success message.
     *
     * @param entity   the updated entity
     * @param changeBy the UserEntity making the change
     * @return a ResponseEntity with status code 200 (OK) and the updated NetEntity as the response body
     * @author Anton Kuzmin
     * @since 2024.03.26
     */
    private ResponseEntity<?> ok(UserEntity entity,
                                 UserEntity changeBy) {
        log("New values:");
        System.out.println(entity.toMap());

        log("Saving entity...");
        entityRepository.save(entity);

        log("Saving history...");
        historyRepository.save(new UserHistory(entity, changeBy));

        log("Success!");
        return ResponseEntity.ok().body(entity);
    }

    /**
     * Inserts a new user into the database.
     *
     * @param username    the username of the user
     * @param rawPassword the password of the user
     * @param fullName    the full name of the user
     * @param title       the title of the user
     * @param description the description of the user
     * @return a ResponseForm indicating the success or failure of the insertion process
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> insert(String username,
                                    String rawPassword,
                                    String fullName,
                                    String title,
                                    String description,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "insert");
        log(String.join(" ", "Insert:", username));

        log("rawPassword:");
        log(rawPassword);
        final String password = encoder.encode(rawPassword);
        log("encodedPassword:");
        log(password);

        log("Checks...");
        if (entityRepository.checkUnique(username))
            return err("Username must be unique");

        UserEntity entity = new UserEntity()
                .update(username,
                        password,
                        fullName,
                        title,
                        description);

        assert entity != null;
        return ok(entity, changeBy);
    }

    /**
     * Updates an existing user in the database.
     *
     * @param id          the ID of the user to update
     * @param username    the new username of the user
     * @param rawPassword the new password of the user
     * @param fullName    the new full name of the user
     * @param title       the new title of the user
     * @param description the new description of the user
     * @return a ResponseForm indicating the success or failure of the update process
     * @author Anton Kuzmin
     * @since 2024.03.26
     */
    public ResponseEntity<?> update(Long id,
                                    String username,
                                    String rawPassword,
                                    String fullName,
                                    String title,
                                    String description,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "update");
        log(String.join(" ", "Update:", username));

        log("rawPassword:");
        log(rawPassword);
        final String password = encoder.encode(rawPassword);
        log("encodedPassword:");
        log(password);

        log("Entity search...");
        UserEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (!entity.getUsername().equals(username) && entityRepository.checkUnique(username))
            return err("Username must be unique");

        if (entity
                .update(username,
                        password,
                        fullName,
                        title,
                        description) == null)
            return err("All parameters are equal");

        return ok(entity, changeBy);
    }

    /**
     * Soft deletes a user entity with the specified ID by marking it as deleted.
     *
     * @param id The ID of the user entity to softly delete.
     * @return The response form indicating the success or failure of the soft delete operation.
     * @author Anton Kuzmin
     * @since 2024.03.26
     */
    public ResponseEntity<?> delete(Long id,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "delete");

        log("Entity search...");
        UserEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (entity.isDeleted())
            return err("Entity already deleted");

        entity.setDeleted(true);

        return ok(entity, changeBy);
    }

}
