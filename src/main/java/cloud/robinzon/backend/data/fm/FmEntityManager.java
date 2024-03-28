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

package cloud.robinzon.backend.data.fm;

import cloud.robinzon.backend.data.fm.resources.FmEntity;
import cloud.robinzon.backend.data.fm.resources.FmEntityRepository;
import cloud.robinzon.backend.data.fm.resources.history.FmHistory;
import cloud.robinzon.backend.data.fm.resources.history.FmHistoryRepository;
import cloud.robinzon.backend.security.tools.JwtUtilStatic;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cloud.robinzon.backend.common.Log.*;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of FM entities in the database.
 * </p>
 * <p>
 * Contains the next methods:
 * </p>
 * <p>
 * {@code insert} - for insert new entity
 * </p>
 * <p>
 * {@code update} - for update an existing entity
 * </p>
 * <p>
 * {@code delete} - for delete the entity
 * </p>
 * <p>
 * Read more in the attached documents for each method.
 * </p>
 *
 * @author Anton Kuzmin
 * @since 2024.03.25
 */

@Service
@AllArgsConstructor
public class FmEntityManager {

    private final FmEntityRepository entityRepository;
    private final FmHistoryRepository historyRepository;

    /**
     * Returns a ResponseEntity with status code 200 (OK) and the updated NetEntity as the response body.
     * Logs the new values of the entity, saves the entity to the repository, saves a new entry in the history repository,
     * and logs the success message.
     *
     * @param entity   the updated entity
     * @param changeBy the UserEntity making the change
     * @return a ResponseEntity with status code 200 (OK) and the updated NetEntity as the response body
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    private ResponseEntity<?> ok(FmEntity entity,
                                 UserEntity changeBy) {
        log("New values:");
        System.out.println(entity.toMap());

        log("Saving entity...");
        entityRepository.save(entity);

        log("Saving history...");
        historyRepository.save(new FmHistory(entity, changeBy));

        log("Success!");
        return ResponseEntity.ok().body(entity);
    }

    /**
     * <h3>Inserts a new entry into the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the new entity parameters and it will be updated.
     * </p>
     *
     * @param name           Name of the entity {@code 50 chars};
     * @param ip             IP address of the entity {@code 15 chars};
     * @param title          Short description of the entry {@code 50 chars};
     * @param specifications Specifications of the entry {@code 255 chars};
     * @param description    Full description of the entry {@code 255 chars};
     * @param price          Price of the entity;
     * @param vm             FM can host VM's or not;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> insert(String name,
                                    String ip,
                                    String title,
                                    String specifications,
                                    String description,
                                    int price,
                                    boolean vm,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "insert");
        log(String.join(" ", "Insert:", name));

        log("Checks...");
        if (entityRepository.checkUnique(ip))
            return err("IP must be unique");

        FmEntity entity = new FmEntity()
                .update(name,
                        ip,
                        title,
                        specifications,
                        description,
                        price,
                        vm);

        return ok(entity, changeBy);
    }

    /**
     * <h3>Updates an existing entry in the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     * </p>
     *
     * @param id             Unique identifier of the entity;
     * @param ip             IP address of the entity {@code 15 chars};
     * @param title          Short description of the entry {@code 50 chars};
     * @param specifications Specifications of the entry {@code 255 chars};
     * @param description    Full description of the entry {@code 255 chars};
     * @param price          Price of the entity;
     * @param vm             FM can host VM's or not;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> update(Long id,
                                    String name,
                                    String ip,
                                    String title,
                                    String specifications,
                                    String description,
                                    int price,
                                    boolean vm,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "update");
        log(String.join(" ", "Update:", name));

        log("Entity search...");
        FmEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (!entity.getIp().equals(ip) && entityRepository.checkUnique(ip))
            return err("IP must be unique");

        if (entity
                .update(name,
                        ip,
                        title,
                        specifications,
                        description,
                        price,
                        vm) == null)
            return err("All parameters are equal");

        return ok(entity, changeBy);
    }

    /**
     * <h3>Deleting an existing entry in the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     * </p>
     *
     * @param id Unique identifier of the entity;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> delete(Long id,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "delete");

        log("Entity search...");
        FmEntity entity = entityRepository.findById(id).orElse(null);
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
