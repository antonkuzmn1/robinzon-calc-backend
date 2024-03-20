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

package cloud.robinzon.backend.security.group;

import cloud.robinzon.backend.security.group.resources.GroupEntity;
import cloud.robinzon.backend.security.group.resources.GroupEntityRepository;
import cloud.robinzon.backend.security.group.resources.history.GroupHistory;
import cloud.robinzon.backend.security.group.resources.history.GroupHistoryRepository;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;

import java.util.Objects;

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
public class GroupEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final GroupEntityRepository entityRepository;
    private final GroupHistoryRepository historyRepository;

    public GroupEntityManager(GroupEntityRepository entityRepository,
                              GroupHistoryRepository historyRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * Inserts a new group into the database.
     *
     * @param name        the name of the group
     * @param title       the title of the group
     * @param description the description of the group
     * @return a ResponseForm indicating the success or failure of the insertion process
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.20
     */
    public ResponseForm insert(String name,
                               String title,
                               String description) {
        super.function("insert");

        String err = setUnique(entityRepository.checkUniqueName(name), "name", name);

        if (!err.isEmpty()) return super.error(err);

        GroupEntity entity = new GroupEntity(
                name,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new GroupHistory(
                entity,
                name,
                title,
                description,
                null));

        return super.success(inserted(name));
    }

    /**
     * Updates an existing group in the database.
     *
     * @param id          the ID of the group to update
     * @param name        the new name of the group
     * @param title       the new title of the group
     * @param description the new description of the group
     * @return a ResponseForm indicating the success or failure of the update process
     * @throws NullPointerException if the group entity with the specified ID is not found in the database
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.20
     */
    public ResponseForm update(Long id,
                               String name,
                               String title,
                               String description)
            throws NullPointerException {
        super.function("update");

        GroupEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = String.join("",
                setUnique(entityRepository.checkUniqueName(name), "name", name),
                setEquals(entity.getName().equals(name)
                        && entity.getTitle().equals(title)
                        && entity.getDescription().equals(description), name));

        if (!err.isEmpty()) return super.error(err);

        entity.update(
                name,
                title,
                description);
        entityRepository.save(entity);

        historyRepository.save(new GroupHistory(
                entity,
                name,
                title,
                description,
                null));

        return super.success(updated(name));
    }

    /**
     * Soft deletes a group from the database by setting its "deleted" flag to true.
     *
     * @param id the ID of the group to softly delete
     * @return a ResponseForm indicating the success or failure of the deletion process
     * @throws NullPointerException  if the group entity with the specified ID is not found in the database
     * @throws NoSuchMethodException if the deleteChecks method is not found
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.20
     */
    public ResponseForm delete(Long id)
            throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        GroupEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = deleteChecks(entity, id);

        if (!err.isEmpty()) return super.error(err);

        entity.setDeleted(true);
        entityRepository.save(entity);
        historyRepository.save(new GroupHistory(entity, null));

        return super.success(deleted(entity.getName()));
    }
}
