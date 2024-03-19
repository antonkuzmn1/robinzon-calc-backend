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

package cloud.robinzon.backend.db.fm;

import cloud.robinzon.backend.db.client.ClientEntity;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
 * @since 2024.03.14
 * @since 2024.03.19
 */

@Service
public class FmEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final FmEntityRepository entityRepository;
    private final FmHistoryRepository historyRepository;
    private final FmRentRepository rentRepository;

    public FmEntityManager(
            FmEntityRepository entityRepository,
            FmHistoryRepository historyRepository,
            FmRentRepository rentRepository) {
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
     * @param name           Name of the entity {@code 50 chars};
     * @param ip             IP address of the entity {@code 15 chars};
     * @param title          Short description of the entry {@code 50 chars};
     * @param specifications Specifications of the entry {@code 255 chars};
     * @param description    Full description of the entry {@code 255 chars};
     * @param price          Price of the entity;
     * @param vm             FM can host VM's or not;
     * @param clientEntity   Renter entity reference;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.14
     * @since 2024.03.19
     */
    @SuppressWarnings("unused")
    public ResponseForm insert(
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm,
            ClientEntity clientEntity) {
        super.function("insert");

        // Checking strings for null value.
        ip = Objects.requireNonNullElse(ip, "");
        title = Objects.requireNonNullElse(title, "");
        specifications = Objects.requireNonNullElse(specifications, "");
        description = Objects.requireNonNullElse(description, "");

        // Checking strings for compliance with entity requirements
        String err = String.join("",
                setNull(name, "name"),
                setChar(name, "name", 50),
                setChar(ip, "ip", 15),
                setLess(price, "price", 0),
                setChar(title, "title", 50),
                setChar(specifications, "specifications", 255),
                setChar(description, "description", 255));

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err);

        // else: all test successfully passed!

        FmEntity entity = new FmEntity(name, ip, title, specifications, description, price, vm, clientEntity);

        entityRepository.save(entity);
        historyRepository.save(new FmHistory(entity, name, ip, title, specifications, description, price, vm, null, false));
        rentRepository.save(new FmRent(entity, clientEntity, null));

        return super.success(inserted(entity.getName()));
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
     * @since 2024.03.14
     * @since 2024.03.19
     */
    public ResponseForm update(
            Long id,
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm
    ) throws NullPointerException {
        super.function("update");

        FmEntity entity = entityRepository.findById(id).orElse(null);

        // Checking strings for null value.
        ip = Objects.requireNonNullElse(ip, "");
        title = Objects.requireNonNullElse(title, "");
        specifications = Objects.requireNonNullElse(specifications, "");
        description = Objects.requireNonNullElse(description, "");

        // Checking strings for compliance with entity requirements
        String err = String.join("",
                setNull(name, "name"),
                setChar(name, "name", 50),
                setChar(ip, "ip", 15),
                setUnique(entityRepository.checkUniqueIp(ip), "ip", ip),
                setLess(price, "price", 0),
                setChar(title, "title", 50),
                setChar(specifications, "specifications", 255),
                setChar(description, "description", 255),
                setFound(entity, id),
                setEquals(entity != null
                        && entity.getName().equals(name)
                        && entity.getIp().equals(ip)
                        && entity.getTitle().equals(title)
                        && entity.getSpecifications()
                        .equals(specifications)
                        && entity.getDescription().equals(description)
                        && entity.getPrice() == price
                        && entity.getVm() == vm, name));

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err);

        // else: all test successfully passed!

        Objects.requireNonNull(entity).update(name, ip, title, specifications, description, price, vm);

        entityRepository.save(entity);
        historyRepository.save(new FmHistory(entity, name, ip, title, specifications, description, price, vm, null, false));

        return super.success(updated(entity.getName()));
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
     * @since 2024.03.14
     * @since 2024.03.19
     */
    @SuppressWarnings("DuplicatedCode")
    public ResponseForm delete(Long id) throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        FmEntity entity = entityRepository.findById(id).orElse(null);

        // Checking strings for compliance with entity requirements
        String err = deleteChecks(entity, id);

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err);

        // else: all test successfully passed!

        Objects.requireNonNull(entity).setDeleted(true);

        entityRepository.save(entity);
        historyRepository.save(new FmHistory(entity, null));

        return super.success(deleted(entity.getName()));
    }

}
