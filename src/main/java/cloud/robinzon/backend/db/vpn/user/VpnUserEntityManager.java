/**
 * Copyright 2024 Anton Kuzmin (http://github.com/antonkuzmn1)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.robinzon.backend.db.vpn.user;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.user.resources.VpnUserEntity;
import cloud.robinzon.backend.db.vpn.user.resources.VpnUserEntityRepository;
import cloud.robinzon.backend.db.vpn.user.resources.history.VpnUserHistory;
import cloud.robinzon.backend.db.vpn.user.resources.history.VpnUserHistoryRepository;
import cloud.robinzon.backend.settings.vpn.type.VpnTypeEntity;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
 * @since 2024.03.20
 */

@SuppressWarnings("unused")
@Service
public class VpnUserEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final VpnUserEntityRepository entityRepository;
    private final VpnUserHistoryRepository historyRepository;

    public VpnUserEntityManager(VpnUserEntityRepository entityRepository,
                                VpnUserHistoryRepository historyRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        super.set(getClass().getSimpleName());
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
     * @since 2024.03.20
     */
    public ResponseForm insert(VpnServerEntity vpnServerEntity,
                               VpnTypeEntity vpnTypeEntity,
                               String ip,
                               String username,
                               String password,
                               String fullName,
                               String title,
                               String description) {
        super.function("insert");

        VpnUserEntity entity = new VpnUserEntity(
                vpnServerEntity,
                vpnTypeEntity,
                ip,
                username,
                password,
                fullName,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new VpnUserHistory(
                entity,
                vpnServerEntity,
                vpnTypeEntity,
                ip,
                username,
                password,
                fullName,
                title,
                description,
                null));

        return super.success(inserted(username));
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
     * @throws NullPointerException If the entity ID does not exist in the repository.
     * @author Anton Kuzmin
     * @since 2024.03.20
     */
    public ResponseForm update(Long id,
                               VpnServerEntity vpnServerEntity,
                               VpnTypeEntity vpnTypeEntity,
                               String ip,
                               String username,
                               String password,
                               String fullName,
                               String title,
                               String description)
            throws NullPointerException {
        super.function("update");

        VpnUserEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = setEquals(entity.getVpnServerEntity().getId().equals(vpnServerEntity.getId())
                && entity.getVpnTypeEntity().getId().equals(vpnTypeEntity.getId())
                && entity.getIp().equals(ip)
                && entity.getUsername().equals(username)
                && entity.getPassword().equals(password)
                && entity.getFullName().equals(fullName)
                && entity.getTitle().equals(title)
                && entity.getDescription().equals(description), ip);

        if (!err.isEmpty()) return super.error(err);

        entity.update(
                vpnServerEntity,
                vpnTypeEntity,
                ip,
                username,
                password,
                fullName,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new VpnUserHistory(
                entity,
                vpnServerEntity,
                vpnTypeEntity,
                ip,
                username,
                password,
                fullName,
                title,
                description,
                null));

        return super.success(updated(ip));
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
     * @since 2024.03.20
     */
    public ResponseForm delete(Long id)
            throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        VpnUserEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = deleteChecks(entity, id);

        if (!err.isEmpty()) return super.error(err);

        entity.setDeleted(true);
        entityRepository.save(entity);
        historyRepository.save(new VpnUserHistory(entity, null));

        return super.success(deleted(entity.getIp()));
    }

}
