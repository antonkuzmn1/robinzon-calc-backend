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

package cloud.robinzon.backend.data.vpn.server;

import cloud.robinzon.backend.common.DeleteForm;
import cloud.robinzon.backend.data.net.resources.NetEntity;
import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntityRepository;
import cloud.robinzon.backend.data.vpn.server.resources.history.VpnServerHistory;
import cloud.robinzon.backend.data.vpn.server.resources.history.VpnServerHistoryRepository;
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.security.jwt.JwtUtil;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

import static cloud.robinzon.backend.common.Allow.check;
import static cloud.robinzon.backend.common.Log.*;

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
     * @since 2024.03.25
     */
    private ResponseEntity<?> ok(VpnServerEntity entity,
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
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @see VpnServerInsertForm
     * @since 2024.03.25
     */
    public ResponseEntity<?> insert(VpnServerInsertForm form) {
        String title = form.getTitle();
        String description = form.getDescription();
        String ip = form.getIp();
        String publicKey = form.getPublicKey();
        NetEntity netEntity = form.getNetEntity();
        Set<VpnTypeEntity> vpnTypeEntity = form.getVpnTypeEntity();
        String token = form.getToken();

        set(getClass(), "insert");
        log(String.join(" ", "Insert:", ip));

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

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
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @see VpnServerUpdateForm
     * @since 2024.03.25
     */
    public ResponseEntity<?> update(VpnServerUpdateForm form) {
        Long id = form.getId();
        String title = form.getTitle();
        String description = form.getDescription();
        String ip = form.getIp();
        String publicKey = form.getPublicKey();
        NetEntity netEntity = form.getNetEntity();
        Set<VpnTypeEntity> vpnTypeEntity = form.getVpnTypeEntity();
        String token = form.getToken();
        set(getClass(), "update");
        log(String.join(" ", "Update:", ip));

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

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
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @see DeleteForm
     * @since 2024.03.25
     */
    public ResponseEntity<?> delete(DeleteForm form) {
        Long id = form.getId();
        String token = form.getToken();

        set(getClass(), "delete");

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

        log("Entity search...");
        VpnServerEntity entity = entityRepository.findById(id).orElse(null);
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