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

package cloud.robinzon.backend.db.net;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.db.net.resources.NetEntityRepository;
import cloud.robinzon.backend.db.net.resources.history.NetHistory;
import cloud.robinzon.backend.db.net.resources.history.NetHistoryRepository;
import cloud.robinzon.backend.db.net.resources.rent.NetRent;
import cloud.robinzon.backend.db.net.resources.rent.NetRentRepository;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.String.format;

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
 * @since 2024.03.14
 * @since 2024.03.19
 * @since 2024.03.20
 */

@SuppressWarnings("unused")
@Service
public class NetEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final NetEntityRepository entityRepository;
    private final NetHistoryRepository historyRepository;
    private final NetRentRepository rentRepository;

    public NetEntityManager(NetEntityRepository entityRepository,
                            NetHistoryRepository historyRepository,
                            NetRentRepository rentRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.rentRepository = rentRepository;
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
     * @param domain       Domain name {@code 50 chars};
     * @param subnet       Subnet IP {@code 15 chars};
     * @param mask         Mask {@code 15 chars};
     * @param dns1         1st DNS IP {@code 15 chars};
     * @param dns2         2nd DNS IP {@code 15 chars};
     * @param dns3         3rd DNS IP {@code 15 chars};
     * @param cloud        Net is in cloud or not {@code 15 chars};
     * @param title        Short description of the entry {@code 50 chars};
     * @param description  Full description of the entry {@code 255 chars};
     * @param clientEntity Renter entity reference;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.14
     * @since 2024.03.19
     * @since 2024.03.20
     */
    public ResponseForm insert(String domain,
                               String subnet,
                               String mask,
                               String dns1,
                               String dns2,
                               String dns3,
                               boolean cloud,
                               String title,
                               String description,
                               ClientEntity clientEntity) {
        super.function("insert");

        String err = setUnique(entityRepository.checkUniqueSubnet(subnet), "subnet", subnet);

        if (!err.isEmpty()) return super.error(err);

        NetEntity entity = new NetEntity(
                clientEntity,
                domain,
                subnet,
                mask,
                dns1,
                dns2,
                dns3,
                cloud,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new NetHistory(
                entity,
                null,
                domain,
                subnet,
                mask,
                dns1,
                dns2,
                dns3,
                cloud,
                title,
                description,
                false));

        rentRepository.save(new NetRent(entity, clientEntity, null));

        return super.success(format("Inserted: %s", subnet));
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
     * @since 2024.03.14
     * @since 2024.03.19
     * @since 2024.03.20
     */
    public ResponseForm update(Long id,
                               String domain,
                               String subnet,
                               String mask,
                               String dns1,
                               String dns2,
                               String dns3,
                               boolean cloud,
                               String title,
                               String description)
            throws NullPointerException {
        super.function("update");

        NetEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = String.join("",
                setUnique(entityRepository.checkUniqueSubnet(subnet), "subnet", subnet),
                setEquals(entity.getDomain().equals(domain)
                        && entity.getSubnet().equals(subnet)
                        && entity.getMask().equals(mask)
                        && entity.getDns1().equals(dns1)
                        && entity.getDns2().equals(dns2)
                        && entity.getDns3().equals(dns3)
                        && entity.isCloud() == cloud
                        && entity.getTitle().equals(title)
                        && entity.getDescription().equals(description), subnet));

        if (!err.isEmpty()) return super.error(err);

        entity.update(
                domain,
                subnet,
                mask,
                dns1,
                dns2,
                dns3,
                cloud,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new NetHistory(
                entity,
                null,
                domain,
                subnet,
                mask,
                dns1,
                dns2,
                dns3,
                cloud,
                title,
                description,
                false));

        return super.success(format("Updated: %s", subnet));
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
     * @since 2024.03.14
     * @since 2024.03.19
     * @since 2024.03.20
     */
    public ResponseForm delete(Long id)
            throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        NetEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = deleteChecks(entity, id);

        if (!err.isEmpty()) return super.error(err);

        entity.setDeleted(true);
        entityRepository.save(entity);
        historyRepository.save(new NetHistory(entity, null));

        return super.success(format("Deleted: %s", entity.getSubnet()));
    }


}
