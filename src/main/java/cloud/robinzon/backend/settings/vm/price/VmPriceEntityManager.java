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

package cloud.robinzon.backend.settings.vm.price;

import cloud.robinzon.backend.settings.vm.price.resources.VmPriceEntity;
import cloud.robinzon.backend.settings.vm.price.resources.VmPriceEntityRepository;
import cloud.robinzon.backend.settings.vm.price.resources.history.VmPriceHistory;
import cloud.robinzon.backend.settings.vm.price.resources.history.VmPriceHistoryRepository;
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
 * @since 2024.03.21
 */

@SuppressWarnings("unused")
public class VmPriceEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final VmPriceEntityRepository entityRepository;
    private final VmPriceHistoryRepository historyRepository;

    public VmPriceEntityManager(VmPriceEntityRepository entityRepository,
                                VmPriceHistoryRepository historyRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * Inserts a new entry into the database with the provided parameter and cost.
     *
     * @param param The parameter to be inserted.
     * @param cost  The cost associated with the parameter.
     * @return ResponseForm indicating the result of the insertion operation.
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.21
     */
    public ResponseForm insert(String param, int cost) {
        super.function("insert");

        String err = setUnique(entityRepository.checkUniqueParam(param), "param", param);

        if (!err.isEmpty()) return super.error(err);

        VmPriceEntity entity = new VmPriceEntity(param, cost);
        entityRepository.save(entity);

        historyRepository.save(new VmPriceHistory(entity, param, cost, null));

        return super.success(format("Inserted: %s", param));
    }

    /**
     * Updates an existing entry in the database with the provided ID, parameter, and cost.
     *
     * @param id    The ID of the entry to update.
     * @param param The new parameter value.
     * @param cost  The new cost value.
     * @return ResponseForm indicating the result of the update operation.
     * @throws NullPointerException if the entity with the given ID is not found.
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.21
     */
    public ResponseForm update(Long id, String param, int cost)
            throws NullPointerException {
        super.function("update");

        VmPriceEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = String.join("",
                setUnique(entityRepository.checkUniqueParam(param), "param", param),
                setEquals(entity.getParam().equals(param)
                        && entity.getCost() == cost, param));

        if (!err.isEmpty()) return super.error(err);

        entity.update(param, cost);
        entityRepository.save(entity);

        historyRepository.save(new VmPriceHistory(entity, param, cost, null));

        return super.success(format("Updated: %s", param));
    }

    /**
     * Soft deletes an entry from the database based on the provided ID.
     *
     * @param id The ID of the entry to be softly deleted.
     * @return ResponseForm indicating the result of the deletion operation.
     * @throws NullPointerException  if the entity with the given ID is not found.
     * @throws NoSuchMethodException if the necessary method is not found.
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.21
     */
    public ResponseForm delete(Long id)
            throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        VmPriceEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = deleteChecks(entity, id);

        if (!err.isEmpty()) return super.error(err);

        entity.setDeleted(true);
        entityRepository.save(entity);
        historyRepository.save(new VmPriceHistory(entity, null));

        return super.success(format("Deleted: %s", entity.getParam()));
    }

}
