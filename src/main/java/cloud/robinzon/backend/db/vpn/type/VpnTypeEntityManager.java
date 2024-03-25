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

package cloud.robinzon.backend.db.vpn.type;

import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntityRepository;
import cloud.robinzon.backend.db.vpn.type.resources.history.VpnTypeHistory;
import cloud.robinzon.backend.db.vpn.type.resources.history.VpnTypeHistoryRepository;
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
public class VpnTypeEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final VpnTypeEntityRepository entityRepository;
    private final VpnTypeHistoryRepository historyRepository;

    public VpnTypeEntityManager(VpnTypeEntityRepository entityRepository,
                                VpnTypeHistoryRepository historyRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * Inserts a new VPN type entry into the database with the provided name.
     *
     * @param name The name of the VPN type to be inserted.
     * @return ResponseForm indicating the result of the insertion operation.
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.21
     */
    public ResponseForm insert(String name) {
        super.function("insert");

        String err = setUnique(entityRepository.checkUniqueName(name), "param", name);

        if (!err.isEmpty()) return super.error(err);

        VpnTypeEntity entity = new VpnTypeEntity(name);
        entityRepository.save(entity);

        historyRepository.save(new VpnTypeHistory(entity, name, null));

        return super.success(format("Inserted: %s", name));
    }

    /**
     * Updates an existing VPN type entry in the database with the provided ID and name.
     *
     * @param id   The ID of the VPN type entry to update.
     * @param name The new name for the VPN type.
     * @return ResponseForm indicating the result of the update operation.
     * @throws NullPointerException if the VPN type with the given ID is not found.
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.21
     */
    public ResponseForm update(Long id, String name)
            throws NullPointerException {
        super.function("update");

        VpnTypeEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = String.join("",
                setUnique(entityRepository.checkUniqueName(name), "name", name),
                setEquals(entity.getName().equals(name), name));

        if (!err.isEmpty()) return super.error(err);

        entity.update(name);
        entityRepository.save(entity);

        historyRepository.save(new VpnTypeHistory(entity, name, null));

        return super.success(format("Updated: %s", name));
    }

    /**
     * Soft deletes a VPN type entry from the database based on the provided ID.
     *
     * @param id The ID of the VPN type entry to be softly deleted.
     * @return ResponseForm indicating the result of the deletion operation.
     * @throws NullPointerException  if the VPN type with the given ID is not found.
     * @throws NoSuchMethodException if the necessary method is not found.
     * @author Anton Kuzmin
     * @see ResponseForm
     * @since 2024.03.21
     */
    public ResponseForm delete(Long id)
            throws NullPointerException, NoSuchMethodException {
        super.function("delete");

        VpnTypeEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = deleteChecks(entity, id);

        if (!err.isEmpty()) return super.error(err);

        entity.setDeleted(true);
        entityRepository.save(entity);
        historyRepository.save(new VpnTypeHistory(entity, null));

        return super.success(format("Deleted: %s", entity.getName()));
    }

}
