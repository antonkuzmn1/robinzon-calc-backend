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

package cloud.robinzon.backend.data.net;

import cloud.robinzon.backend.data.net.resources.NetEntity;
import cloud.robinzon.backend.data.net.resources.NetEntityRepository;
import cloud.robinzon.backend.data.net.resources.history.NetHistory;
import cloud.robinzon.backend.data.net.resources.history.NetHistoryRepository;
import cloud.robinzon.backend.security.tools.JwtUtilStatic;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cloud.robinzon.backend.common.Log.*;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of Net entities in the database.
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
public class NetEntityManager {

    private final NetEntityRepository entityRepository;
    private final NetHistoryRepository historyRepository;

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
    private ResponseEntity<?> ok(NetEntity entity,
                                 UserEntity changeBy) {
        log("New values:");
        System.out.println(entity.toMap());

        log("Saving entity...");
        entityRepository.save(entity);

        log("Saving history...");
        historyRepository.save(new NetHistory(entity, changeBy));

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
     * @param domain      Domain name {@code 50 chars};
     * @param subnet      Subnet IP {@code 15 chars};
     * @param mask        Mask {@code 15 chars};
     * @param dns1        1st DNS IP {@code 15 chars};
     * @param dns2        2nd DNS IP {@code 15 chars};
     * @param dns3        3rd DNS IP {@code 15 chars};
     * @param cloud       Net is in cloud or not {@code 15 chars};
     * @param title       Short description of the entry {@code 50 chars};
     * @param description Full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> insert(String domain,
                                    String subnet,
                                    String mask,
                                    String dns1,
                                    String dns2,
                                    String dns3,
                                    boolean cloud,
                                    String title,
                                    String description,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "insert");
        log(String.join(" ", "Insert:", subnet));

        log("Checks...");
        if (entityRepository.checkUnique(subnet))
            return err("Subnet must be unique");

        NetEntity entity = new NetEntity()
                .update(domain,
                        subnet,
                        mask,
                        dns1,
                        dns2,
                        dns3,
                        cloud,
                        title,
                        description);

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
     * @param id          Unique identifier of the entity;
     * @param subnet      Subnet IP {@code 15 chars};
     * @param mask        Mask {@code 15 chars};
     * @param dns1        1st DNS IP {@code 15 chars};
     * @param dns2        2nd DNS IP {@code 15 chars};
     * @param dns3        3rd DNS IP {@code 15 chars};
     * @param cloud       Net is in cloud or not {@code 15 chars};
     * @param title       Short description of the entry {@code 50 chars};
     * @param description Full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> update(Long id,
                                    String domain,
                                    String subnet,
                                    String mask,
                                    String dns1,
                                    String dns2,
                                    String dns3,
                                    boolean cloud,
                                    String title,
                                    String description,
                                    String token) {
        UserEntity changeBy = JwtUtilStatic.extractEntity(token);
        boolean allow = changeBy.isAdmin();
        if (!allow) return err("Access denied");

        set(getClass(), "update");
        log(String.join(" ", "Update:", subnet));

        log("Entity search...");
        NetEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (!entity.getSubnet().equals(subnet) && entityRepository.checkUnique(subnet))
            return err("Subnet must be unique");

        if (entity
                .update(domain,
                        subnet,
                        mask,
                        dns1,
                        dns2,
                        dns3,
                        cloud,
                        title,
                        description) == null)
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
     * @param id - the unique identifier of the entity;
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
        NetEntity entity = entityRepository.findById(id).orElse(null);
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
