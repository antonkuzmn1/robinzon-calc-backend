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

package cloud.robinzon.backend.db.vm;

import cloud.robinzon.backend.db.fm.resources.FmEntity;
import cloud.robinzon.backend.db.vm.resources.VmEntity;
import cloud.robinzon.backend.db.vm.resources.VmEntityRepository;
import cloud.robinzon.backend.db.vm.resources.history.VmHistory;
import cloud.robinzon.backend.db.vm.resources.history.VmHistoryRepository;
import cloud.robinzon.backend.db.vm.resources.rent.VmRent;
import cloud.robinzon.backend.db.vm.resources.rent.VmRentRepository;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of VM entities in the database.
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
 * @since 2024.03.20
 */

@SuppressWarnings("unused")
@Service
public class VmEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    private final VmEntityRepository entityRepository;
    private final VmHistoryRepository historyRepository;
    private final VmRentRepository rentRepository;

    public VmEntityManager(VmEntityRepository entityRepository,
                           VmHistoryRepository historyRepository,
                           VmRentRepository rentRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.rentRepository = rentRepository;
        super.set(getClass().getSimpleName());
    }

    /**
     * <h3>Inserts a new entry into the database.</h3>
     * <p>
     * <strong>IMPORTANT:</strong>
     * </p>
     * <p>
     * If entity already exists in database,
     * it will be just updated.
     * keep calm :)
     * </p>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the new entity parameters and it will be updated.
     * </p>
     *
     * @param id       Unique identifier of the entry {@code 36 chars};
     * @param name     Name of the entry {@code 50 chars};
     * @param cpu      Amount of CPU cores;
     * @param ram      Amount of RAM;
     * @param ssd      Amount of SSD;
     * @param hdd      Amount of HDD;
     * @param running  VM's state;
     * @param fmEntity FM entity on which this virtual machine is hosted;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.14
     * @since 2024.03.20
     */
    public ResponseForm insert(String id, String name, int cpu, int ram, int ssd, int hdd, boolean running, FmEntity fmEntity) {
        super.function("insert");

        VmEntity entity = entityRepository.findById(id).orElse(null);

        String err = setEquals(entity != null
                && entity.getName().equals(name)
                && entity.getCpu() == cpu
                && entity.getRam() == ram
                && entity.getSsd() == ssd
                && entity.getHdd() == hdd
                && entity.isRunning() == running
                && entity.getFmEntity().equals(fmEntity), name);

        if (!err.isEmpty()) return super.error(err);

        if (entity == null) {

            entity = new VmEntity(id, name, cpu, ram, ssd, hdd, running, fmEntity);
            entityRepository.save(entity);
            historyRepository.save(new VmHistory(entity, name, cpu, ram, ssd, hdd, running, fmEntity, null, true));
            rentRepository.save(new VmRent(entity, null, null));

            return super.success(inserted(name));
        } else {

            entity.update(name, cpu, ram, ssd, hdd, running, fmEntity);
            entityRepository.save(entity);
            historyRepository.save(new VmHistory(entity, name, cpu, ram, ssd, hdd, running, fmEntity, null, false));

            return super.success(updated(name));
        }
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
     * @param id          Unique identifier of the entry {@code 36 chars};
     * @param title       Short description of the entry {@code 50 chars};
     * @param description Full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.14
     * @since 2024.03.20
     */
    public ResponseForm update(String id, String title, String description) {
        super.function("update");

        VmEntity entity = Objects.requireNonNull(entityRepository.findById(id).orElse(null));

        String err = setEquals(entity.getTitle().equals(title)
                && entity.getDescription().equals(description), entity.getName());

        if (!err.isEmpty()) return super.error(err);

        entity.update2(title, description);
        entityRepository.save(entity);
        historyRepository.save(new VmHistory(entity, title, description, null));

        return super.success(updated(entity.getName()));
    }

    /**
     * <h3>Soft deletion of all entities from the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     * </p>
     *
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.14
     * @since 2024.03.20
     */
    public ResponseForm deleteAll() {
        super.function("deleteAll");

        entityRepository.markAsDeletedAll();

        return super.success("All VM has been successfully marked as deleted");
    }

}
