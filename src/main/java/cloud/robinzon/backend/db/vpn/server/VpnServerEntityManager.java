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

package cloud.robinzon.backend.db.vpn.server;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntityRepository;
import cloud.robinzon.backend.db.vpn.server.resources.history.VpnServerHistory;
import cloud.robinzon.backend.db.vpn.server.resources.history.VpnServerHistoryRepository;
import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

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
 * @since 2024.03.25
 */

@Service
@AllArgsConstructor
public final class VpnServerEntityManager {

    private final VpnServerEntityRepository entityRepository;
    private final VpnServerHistoryRepository historyRepository;

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
    private ResponseEntity<?> ok(VpnServerEntity entity,
                                 @SuppressWarnings("SameParameterValue")
                                 UserEntity changeBy) {
        log("New values:");
        System.out.println(entity.toMap());

        log("Saving entity...");
        entityRepository.save(entity);

        log("Saving history...");
        historyRepository.save(new VpnServerHistory(entity, changeBy));

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
     * @param title         Short description of the entry {@code 50 chars};
     * @param description   Full description of the entry {@code 255 chars};
     * @param ip            IP address {@code 15 chars};
     * @param publicKey     Public key {@code 50 chars};
     * @param netEntity     NetEntity reference;
     * @param vpnTypeEntity VPN type entity-list;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> insert(String title,
                                    String description,
                                    String ip,
                                    String publicKey,
                                    NetEntity netEntity,
                                    Set<VpnTypeEntity> vpnTypeEntity) {
        set(getClass(), "insert");
        log(String.join(" ", "Insert:", ip));

        log("Checks...");
        if (entityRepository.checkUnique(ip))
            return err("IP must be unique");

        VpnServerEntity entity = new VpnServerEntity()
                .update(title,
                        description,
                        ip,
                        publicKey,
                        netEntity,
                        vpnTypeEntity);

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
     * @param id            Unique identifier of the entity;
     * @param title         Short description of the entity {@code 50 chars};
     * @param description   Full description of the entity {@code 255 chars};
     * @param ip            IP address {@code 15 chars};
     * @param publicKey     Public key {@code 50 chars};
     * @param netEntity     Reference to a NetEntity object;
     * @param vpnTypeEntity List of VPN type entities;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public ResponseEntity<?> update(Long id,
                                    String title,
                                    String description,
                                    String ip,
                                    String publicKey,
                                    NetEntity netEntity,
                                    Set<VpnTypeEntity> vpnTypeEntity) {
        set(getClass(), "update");
        log(String.join(" ", "Update:", ip));

        log("Entity search...");
        VpnServerEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (!entity.getIp().equals(ip) && entityRepository.checkUnique(ip))
            return err("IP must be unique");

        if (entity
                .update(title,
                        description,
                        ip,
                        publicKey,
                        netEntity,
                        vpnTypeEntity) == null)
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
     * @since 2024.03.25
     */
    public ResponseEntity<?> delete(Long id) {
        set(getClass(), "delete");

        log("Entity search...");
        VpnServerEntity entity = entityRepository.findById(id).orElse(null);
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