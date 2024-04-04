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

package cloud.robinzon.backend.data.vpn.type;

import cloud.robinzon.backend.common.DeleteForm;
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntityRepository;
import cloud.robinzon.backend.data.vpn.type.resources.history.VpnTypeHistory;
import cloud.robinzon.backend.data.vpn.type.resources.history.VpnTypeHistoryRepository;
import cloud.robinzon.backend.security.jwt.JwtUtil;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cloud.robinzon.backend.common.Allow.check;
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
 * @since 2024.03.26
 */

@Service
@AllArgsConstructor
public class VpnTypeEntityManager {

    private final VpnTypeEntityRepository entityRepository;
    private final VpnTypeHistoryRepository historyRepository;
    private final JwtUtil jwtUtil;

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
    private ResponseEntity<?> ok(VpnTypeEntity entity,
                                 UserEntity changeBy) {
        log("New values:");
        System.out.println(entity.toMap());

        log("Saving entity...");
        entityRepository.save(entity);

        log("Saving history...");
        historyRepository.save(new VpnTypeHistory(entity, changeBy));

        log("Success!");
        return ResponseEntity.ok().body(entity);
    }

    /**
     * Inserts a new VPN type entry into the database with the provided name.
     *
     * @return ResponseForm indicating the result of the insertion operation.
     * @author Anton Kuzmin
     * @see VpnTypeInsertForm
     * @since 2024.03.26
     */
    public ResponseEntity<?> insert(VpnTypeInsertForm form) {
        String name = form.getName();
        String token = form.getToken();

        set(getClass(), "insert");
        log(String.join(" ", "Insert:", name));

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

        log("Checks...");
        if (entityRepository.checkUnique(name))
            return err("Name must be unique");

        VpnTypeEntity entity = new VpnTypeEntity(name);

        return ok(entity, changeBy);
    }

    /**
     * Updates an existing VPN type entry in the database with the provided ID and name.
     *
     * @return ResponseForm indicating the result of the update operation.
     * @author Anton Kuzmin
     * @see VpnTypeUpdateForm
     * @since 2024.03.26
     */
    public ResponseEntity<?> update(VpnTypeUpdateForm form) {
        Long id = form.getId();
        String name = form.getName();
        String token = form.getToken();

        set(getClass(), "update");
        log(String.join(" ", "Update:", name));

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

        log("Entity search...");
        VpnTypeEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (!entity.getName().equals(name) && entityRepository.checkUnique(name))
            return err("Name must be unique");

        if (entity
                .update(name) == null)
            return err("All parameters are equal");

        return ok(entity, changeBy);
    }

    /**
     * Soft deletes a VPN type entry from the database based on the provided ID.
     *
     * @return ResponseForm indicating the result of the deletion operation.
     * @author Anton Kuzmin
     * @see DeleteForm
     * @since 2024.03.26
     */
    public ResponseEntity<?> delete(DeleteForm form) {
        Long id = form.getId();
        String token = form.getToken();

        set(getClass(), "delete");

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

        log("Entity search...");
        VpnTypeEntity entity = entityRepository.findById(id).orElse(null);
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
