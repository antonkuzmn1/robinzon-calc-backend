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

package cloud.robinzon.backend.db.vpn.server;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntityRepository;
import cloud.robinzon.backend.db.vpn.server.resources.history.VpnServerHistory;
import cloud.robinzon.backend.db.vpn.server.resources.history.VpnServerHistoryRepository;
import cloud.robinzon.backend.settings.vpn.type.VpnTypeEntity;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

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
 * @since 2024.03.13
 * @since 2024.03.20
 */

@SuppressWarnings("unused")
@Service
public final class VpnServerEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final VpnServerEntityRepository entityRepository;
    private final VpnServerHistoryRepository historyRepository;

    public VpnServerEntityManager(VpnServerEntityRepository entityRepository,
                                  VpnServerHistoryRepository historyRepository) {
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
     * @since 2024.03.13
     * @since 2024.03.20
     */
    public ResponseForm insert(String title,
                               String description,
                               String ip,
                               String publicKey,
                               NetEntity netEntity,
                               Set<VpnTypeEntity> vpnTypeEntity) {
        super.function("insert");

        VpnServerEntity entity = new VpnServerEntity(
                title,
                description,
                ip,
                publicKey,
                netEntity,
                vpnTypeEntity);
        entityRepository.save(entity);

        historyRepository.save(new VpnServerHistory(
                entity,
                title,
                description,
                ip,
                publicKey,
                netEntity,
                vpnTypeEntity,
                null));

        return super.success(inserted(ip));
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
     * @since 2024.03.13
     * @since 2024.03.20
     */
    public ResponseForm update(Long id,
                               String title,
                               String description,
                               String ip,
                               String publicKey,
                               NetEntity netEntity,
                               Set<VpnTypeEntity> vpnTypeEntity)
            throws NullPointerException {
        super.function("update");

        VpnServerEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = setEquals(entity.getTitle().equals(title)
                        && entity.getDescription().equals(description)
                        && entity.getIp().equals(ip)
                        && entity.getPublicKey().equals(publicKey)
                        && entity.getNetEntity().getId().equals(netEntity.getId())
                        && entity.getVpnTypeEntity().equals(vpnTypeEntity), ip);

        if (!err.isEmpty()) return super.error(err);

        entity.update(
                title,
                description,
                ip,
                publicKey,
                netEntity,
                vpnTypeEntity);
        entityRepository.save(entity);

        historyRepository.save(new VpnServerHistory(
                entity,
                title,
                description,
                ip,
                publicKey,
                netEntity,
                vpnTypeEntity,
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
     * @since 2024.03.13
     * @since 2024.03.20
     */
    public ResponseForm delete(Long id)
            throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        VpnServerEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = deleteChecks(entity, id);

        if (!err.isEmpty()) return super.error(err);

        entity.setDeleted(true);
        entityRepository.save(entity);
        historyRepository.save(new VpnServerHistory(entity, null));

        return super.success(deleted(entity.getIp()));
    }

}