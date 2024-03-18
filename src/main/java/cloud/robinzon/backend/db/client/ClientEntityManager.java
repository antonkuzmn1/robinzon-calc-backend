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

package cloud.robinzon.backend.db.client;

import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of Entities in the database.
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
 * @since 2024.03.18
 */

@Service
public final class ClientEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final ClientEntityRepository entityRepository;
    private final ClientHistoryRepository historyRepository;
    private final ClientPaymentRepository paymentRepository;

    private ClientEntityManager(
            ClientEntityRepository entityRepository,
            ClientHistoryRepository historyRepository,
            ClientPaymentRepository paymentRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.paymentRepository = paymentRepository;
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
     * @param name           Client name {@code 50 chars};
     * @param inn            Client's TIN (INN) {@code 12 chars};
     * @param discount       Customer discount amount;
     * @param contractNumber Contract number;
     * @param contractDate   Date of conclusion of the contract;
     * @param title          The short description of the entry {@code 50 chars};
     * @param balance        Client balance;
     * @param description    The full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.18
     */
    @SuppressWarnings({"RedundantThrows", "unused"})
    public ResponseForm insert(
            String name,
            String inn,
            int discount,
            int contractNumber,
            Date contractDate,
            String title,
            int balance,
            String description) {

        // Setting the function name for the logging class.
        super.function(insert);

        // Checking strings for null value.
        title = Objects.requireNonNullElse(title, "");
        description = Objects.requireNonNullElse(description, "");

        // Checking strings for compliance with entity requirements
        err.append(String.join("",
                setNull(name, "name"),
                setChar(name, "name", 100),
                setUnique(entityRepository.checkUniqueInn(inn), "inn", inn),
                setChar(inn, "inn", 12),
                setLess(discount, "discount", 0),
                setMore(discount, "discount", 100),
                setLess(contractNumber, "contractNumber", 1),
                setUnique(entityRepository.checkUniqueContractNumber(contractNumber), "contractNumber", contractNumber),
                setChar(title, "Title", 50),
                setChar(description, "Description", 255)));

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err.toString());

        // Creating a new entity
        ClientEntity entity = new ClientEntity(name, inn, discount, contractNumber, contractDate, title, balance, description);
        entityRepository.save(entity);

        // Adding a new entry to the entity editing history.
        historyRepository.save(new ClientHistory(entity, name, inn, discount, contractNumber, contractDate, title, description, null, false));

        // Adding a new entry to the entity payments history.
        paymentRepository.save(new ClientPayment(entity, balance, null));

        // The function execution was successful!
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
     * @param id             The unique identifier of the entity;
     * @param name           Client name {@code 50 chars};
     * @param inn            Client's TIN (INN) {@code 12 chars};
     * @param discount       Customer discount amount;
     * @param contractNumber Contract number;
     * @param contractDate   Date of conclusion of the contract;
     * @param title          The short description of the entry {@code 50 chars};
     * @param description    The full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.18
     */
    public ResponseForm update(
            Long id,
            String name,
            String inn,
            int discount,
            int contractNumber,
            Date contractDate,
            String title,
            String description
    ) throws NullPointerException {

        // Setting the function name for the logging class.
        super.function(update);

        // Searching for an entity by ID in the repository.
        ClientEntity entity = entityRepository.findById(id).orElse(null);

        // Checking strings for null value.
        title = Objects.requireNonNullElse(title, "");
        description = Objects.requireNonNullElse(description, "");

        // Checking strings for compliance with entity requirements
        err.append(String.join("",
                setNull(name, "name"),
                setChar(name, "name", 100),
                setUnique(entityRepository.checkUniqueInn(inn), "inn", inn),
                setChar(inn, "inn", 12),
                setLess(discount, "discount", 0),
                setMore(discount, "discount", 100),
                setLess(contractNumber, "contractNumber", 1),
                setUnique(entityRepository.checkUniqueContractNumber(contractNumber), "contractNumber", contractNumber),
                setChar(title, "Title", 50),
                setChar(description, "Description", 255),
                setUnique(entity == null, "id", id),
                setEquals(entity != null
                        && entity.getName().equals(name)
                        && entity.getInn().equals(inn)
                        && entity.getDiscount() == discount
                        && entity.getContractNumber() == contractNumber
                        && entity.getContractDate().equals(contractDate)
                        && entity.getTitle().equals(title)
                        && entity.getDescription().equals(description), name)));

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err.toString());

        // Setting new values.
        Objects.requireNonNull(entity).update(name, inn, discount, contractNumber, contractDate, title, description);
        entityRepository.save(entity);

        // Insert new value into history.
        historyRepository.save(new ClientHistory(entity, name, inn, discount, contractNumber, contractDate, title, description, null, false));

        // The function execution was successful!
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
     * @param id The unique identifier of the entity;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.18
     */
    public ResponseForm delete(Long id) throws NullPointerException {

        // Setting the function name for the logging class.
        super.function(delete);

        // Searching for an entity by ID in the repository.
        ClientEntity entity = entityRepository.findById(id).orElse(null);

        // Checking strings for compliance with entity requirements
        err.append(String.join("",
                setNull(id, "id"),
                setLongLess(id, "ID", 1),
                setFound(entity, id),
                setDeleted(entity != null && entity.isDeleted(), id)));

        // Termination of the function if errors were detected.
        if (!err.isEmpty()) return super.error(err.toString());

        // Setting new values.
        Objects.requireNonNull(entity).setDeleted(true);
        entityRepository.save(entity);

        // Adding a new entry to the entity editing history.
        historyRepository.save(new ClientHistory(entity, null));

        // The function execution was successful!
        return super.success(deleted(entity.getName()));
    }
}
