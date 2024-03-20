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

package cloud.robinzon.backend.security.user;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.security.user.resources.UserEntityRepository;
import cloud.robinzon.backend.security.user.resources.history.UserHistory;
import cloud.robinzon.backend.security.user.resources.history.UserHistoryRepository;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;

import java.util.Objects;

import static java.lang.String.format;

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
 * @see ResponseForm
 * @see ResponseStringTemplates
 * @since 2024.03.20
 */

@SuppressWarnings("unused")
public class UserEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final UserEntityRepository entityRepository;
    private final UserHistoryRepository historyRepository;

    public UserEntityManager(UserEntityRepository entityRepository,
                             UserHistoryRepository historyRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * Inserts a new user into the database.
     *
     * @param username    the username of the user
     * @param password    the password of the user
     * @param fullName    the full name of the user
     * @param title       the title of the user
     * @param description the description of the user
     * @return a ResponseForm indicating the success or failure of the insertion process
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.20
     */
    public ResponseForm insert(String username,
                               String password,
                               String fullName,
                               String title,
                               String description) {
        super.function("insert");

        String err = setUnique(entityRepository.checkUniqueUsername(username), "username", username);

        if (!err.isEmpty()) return super.error(err);

        UserEntity entity = new UserEntity(
                username,
                password,
                fullName,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new UserHistory(
                entity,
                username,
                password,
                fullName,
                title,
                description,
                null));

        return super.success(format("Inserted: %s", username));
    }

    /**
     * Updates an existing user in the database.
     *
     * @param id          the ID of the user to update
     * @param username    the new username of the user
     * @param password    the new password of the user
     * @param fullName    the new full name of the user
     * @param title       the new title of the user
     * @param description the new description of the user
     * @return a ResponseForm indicating the success or failure of the update process
     * @throws NullPointerException if the user entity with the specified ID is not found in the database
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.20
     */
    public ResponseForm update(Long id,
                               String username,
                               String password,
                               String fullName,
                               String title,
                               String description)
            throws NullPointerException {
        super.function("update");

        UserEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = String.join("",
                setUnique(entityRepository.checkUniqueUsername(username), "username", username),
                setEquals(entity.getUsername().equals(username)
                        && entity.getPassword().equals(password)
                        && entity.getFullName().equals(fullName)
                        && entity.getTitle().equals(title)
                        && entity.getDescription().equals(description), username));

        if (!err.isEmpty()) return super.error(err);

        entity.update(
                username,
                password,
                fullName,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new UserHistory(
                entity,
                username,
                password,
                fullName,
                title,
                description,
                null));

        return super.success(format("Updated: %s", username));
    }

    /**
     * Soft deletes a user entity with the specified ID by marking it as deleted.
     *
     * @param id The ID of the user entity to softly delete.
     * @return The response form indicating the success or failure of the soft delete operation.
     * @throws NullPointerException  If the entity is not found.
     * @throws NoSuchMethodException If a required method is not found.
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.20
     */
    public ResponseForm delete(Long id)
            throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        UserEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = deleteChecks(entity, id);

        if (!err.isEmpty()) return super.error(err);

        entity.setDeleted(true);
        entityRepository.save(entity);
        historyRepository.save(new UserHistory(entity, null));

        return super.success(format("Deleted: %s", entity.getUsername()));
    }

}
