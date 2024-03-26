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

package cloud.robinzon.backend.db.vpn.user;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.db.vpn.user.resources.VpnUserEntity;
import cloud.robinzon.backend.db.vpn.user.resources.VpnUserEntityRepository;
import cloud.robinzon.backend.db.vpn.user.resources.history.VpnUserHistory;
import cloud.robinzon.backend.db.vpn.user.resources.history.VpnUserHistoryRepository;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cloud.robinzon.backend.tools.Log.*;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of VPN server entities in the database.
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
 * @since 2024.03.26
 */

@Service
@AllArgsConstructor
public class VpnUserEntityManager {

    private final VpnUserEntityRepository entityRepository;
    private final VpnUserHistoryRepository historyRepository;

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
    private ResponseEntity<?> ok(VpnUserEntity entity,
                                 @SuppressWarnings("SameParameterValue")
                                 UserEntity changeBy) {
        log("New values:");
        System.out.println(entity.toMap());

        log("Saving entity...");
        entityRepository.save(entity);

        log("Saving history...");
        historyRepository.save(new VpnUserHistory(entity, changeBy));

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
     * @param vpnServerEntity VpnServerEntity object representing the VPN server entity to be inserted.
     * @param vpnTypeEntity   A VpnTypeEntity object representing the VPN type entity to be inserted.
     * @param ip              The IP address to be associated with the entry.
     * @param username        The username to be associated with the entry.
     * @param password        The password to be associated with the entry.
     * @param fullName        The full name to be associated with the entry.
     * @param title           The title of the entry.
     * @param description     The description of the entry.
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.26
     */
    public ResponseEntity<?> insert(VpnServerEntity vpnServerEntity,
                               VpnTypeEntity vpnTypeEntity,
                               String ip,
                               String username,
                               String password,
                               String fullName,
                               String title,
                               String description) {
        set(getClass(), "insert");
        log(String.join(" ", "Insert:", username));

        VpnUserEntity entity = new VpnUserEntity()
                .update(vpnServerEntity,
                        vpnTypeEntity,
                        ip,
                        username,
                        password,
                        fullName,
                        title,
                        description);

        return ok(entity, null);
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
     * @param id              The ID of the VPN user entity to be updated. {@link Long}
     * @param vpnServerEntity A VpnServerEntity object representing the VPN server entity for the updated entry.
     * @param vpnTypeEntity   A VpnTypeEntity object representing the VPN type entity for the updated entry.
     * @param ip              The updated IP address associated with the entry.
     * @param username        The updated username associated with the entry.
     * @param password        The updated password associated with the entry.
     * @param fullName        The updated full name associated with the entry.
     * @param title           The updated title of the entry.
     * @param description     The updated description of the entry.
     * @return A standard response form that contains the class name, functions, status, and text.
     * @author Anton Kuzmin
     * @since 2024.03.26
     */
    public ResponseEntity<?> update(Long id,
                               VpnServerEntity vpnServerEntity,
                               VpnTypeEntity vpnTypeEntity,
                               String ip,
                               String username,
                               String password,
                               String fullName,
                               String title,
                               String description) {
        set(getClass(), "update");
        log(String.join(" ", "Update:", username));

        log("Entity search...");
        VpnUserEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        if (entity
                .update(vpnServerEntity,
                        vpnTypeEntity,
                        ip,
                        username,
                        password,
                        fullName,
                        title,
                        description) == null)
            return err("All parameters are equal");

        return ok(entity, null);
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
     * @since 2024.03.26
     */
    public ResponseEntity<?> delete(Long id) {
        set(getClass(), "delete");

        log("Entity search...");
        VpnUserEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (entity.isDeleted())
            return err("Entity already deleted");

        entity.setDeleted(true);

        return ok(entity, null);
    }

}
