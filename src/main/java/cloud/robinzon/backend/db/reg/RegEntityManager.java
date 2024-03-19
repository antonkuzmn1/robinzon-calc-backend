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

package cloud.robinzon.backend.db.reg;

import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of Reg entities in the database.
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
public class RegEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final RegEntityRepository entityRepository;
    private final RegHistoryRepository historyRepository;

    public RegEntityManager(
            RegEntityRepository entityRepository,
            RegHistoryRepository historyRepository) {
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
     * @param brand          Name of the brand {@code 100 chars};
     * @param name           Name of the entity {@code 100 chars};
     * @param part           The part {@code 50 chars};
     * @param serial         Serial number of the entity {@code 50 chars};
     * @param buyDate        Entity purchase date;
     * @param warrantyMonths Amount of warranty months;
     * @param provider       Name of the provider (dealer) {@code 50 chars};
     * @param title          Short description of the entry {@code 50 chars};
     * @param description    Full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.14
     * @since 2024.03.19
     */
    @SuppressWarnings({"unused", "DuplicatedCode"})
    public ResponseForm insert(
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String provider,
            String title,
            String description) {
        super.function("insert");

        // Checking strings for null value.
        brand = Objects.requireNonNullElse(brand, "");
        part = Objects.requireNonNullElse(part, "");
        serial = Objects.requireNonNullElse(serial, "");
        provider = Objects.requireNonNullElse(provider, "");
        title = Objects.requireNonNullElse(title, "");
        description = Objects.requireNonNullElse(description, "");

        // Checking strings for compliance with entity requirements
        String err = String.join("",
                setChar(brand, "brand", 100),
                setNull(brand, "brand"),
                setChar(name, "name", 100),
                setChar(part, "part", 50),
                setChar(serial, "serial", 50),
                setNull(buyDate, "buyDate"),
                setLess(warrantyMonths, "warrantyMonths", 0),
                setChar(provider, "provider", 50),
                setChar(title, "title", 50),
                setChar(description, "description", 255));

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err);

        // else: all test successfully passed!

        RegEntity entity = new RegEntity(brand, name, part, serial, buyDate, warrantyMonths, provider, title, description);

        entityRepository.save(entity);
        historyRepository.save(new RegHistory(entity, brand, name, part, serial, buyDate, warrantyMonths, provider, title, description, null, false));

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
     * @param brand          Name of the brand {@code 100 chars};
     * @param name           Name of the entity {@code 100 chars};
     * @param part           The part {@code 50 chars};
     * @param serial         Serial number of the entity {@code 50 chars};
     * @param buyDate        Entity purchase date;
     * @param warrantyMonths Amount of warranty months;
     * @param provider       Name of the provider (dealer) {@code 50 chars};
     * @param title          Short description of the entry {@code 50 chars};
     * @param description    Full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.14
     * @since 2024.03.19
     */
    @SuppressWarnings("DuplicatedCode")
    public ResponseForm update(
            Long id,
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String provider,
            String title,
            String description
    ) throws NullPointerException {
        super.function("update");

        RegEntity entity = entityRepository.findById(id).orElse(null);

        // Checking strings for null value.
        brand = Objects.requireNonNullElse(brand, "");
        part = Objects.requireNonNullElse(part, "");
        serial = Objects.requireNonNullElse(serial, "");
        provider = Objects.requireNonNullElse(provider, "");
        title = Objects.requireNonNullElse(title, "");
        description = Objects.requireNonNullElse(description, "");

        // Checking strings for compliance with entity requirements
        String err = String.join("",
                setChar(brand, "brand", 100),
                setNull(brand, "brand"),
                setChar(name, "name", 100),
                setChar(part, "part", 50),
                setChar(serial, "serial", 50),
                setNull(buyDate, "buyDate"),
                setLess(warrantyMonths, "warrantyMonths", 0),
                setChar(provider, "provider", 50),
                setChar(title, "title", 50),
                setChar(description, "description", 255),
                setFound(entity, id),
                setEquals(entity != null
                        && entity.getBrand().equals(brand)
                        && entity.getName().equals(name)
                        && entity.getPart().equals(part)
                        && entity.getSerial().equals(serial)
                        && entity.getBuyDate().equals(buyDate)
                        && entity.getWarrantyMonths() == warrantyMonths
                        && entity.getProvider().equals(provider)
                        && entity.getTitle().equals(title)
                        && entity.getDescription().equals(description), name));

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err);

        // else: all test successfully passed!

        Objects.requireNonNull(entity).update(brand, name, part, serial, buyDate, warrantyMonths, title, description, provider);

        entityRepository.save(entity);
        historyRepository.save(new RegHistory(entity, brand, name, part, serial, buyDate, warrantyMonths, provider, title, description, null, false));

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
    public ResponseForm delete(Long id) throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        RegEntity entity = entityRepository.findById(id).orElse(null);

        // Checking strings for compliance with entity requirements
        String err = deleteChecks(entity, id);

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err);

        // else: all test successfully passed!

        Objects.requireNonNull(entity).setDeleted(true);

        entityRepository.save(entity);
        historyRepository.save(new RegHistory(entity, null));

        return super.success(deleted(entity.getName()));
    }

}
