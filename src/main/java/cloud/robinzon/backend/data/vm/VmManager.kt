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

package cloud.robinzon.backend.data.vm

import cloud.robinzon.backend.data.fm.resources.FmEntity
import cloud.robinzon.backend.data.vm.resources.VmEntityManager
import cloud.robinzon.backend.data.vm.resources.rent.VmRentManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * ## Entity Management Tools
 *
 * This class handles the management of Entities in the database.
 *
 * @author Anton Kuzmin
 * @see VmEntityManager
 * @see VmRentManager
 * @since 2024.03.28
 * @version 2024.03.28
 */

@Service
class VmManager(
        private val e: VmEntityManager,
        private val r: VmRentManager
) {

    /**
     * ## Inserts a new entry into the database.
     *
     * **IMPORTANT:**
     *
     * If entity already exists in database,
     * it will be just updated.
     * keep calm :)
     *
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the new entity parameters and it will be updated.
     *
     * @param id       Unique identifier of the entry `36 chars`;
     * @param name     Name of the entry `50 chars`;
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
     * @since 2024.03.25
     */
    fun insert(id: String,
               name: String,
               cpu: Int,
               ram: Int,
               ssd: Int,
               hdd: Int,
               running: Boolean,
               fmEntity: FmEntity,
               title: String,
               description: String,
               token: String
    ): ResponseEntity<*> {
        return e.insert(id, name, cpu, ram, ssd, hdd, running, fmEntity, title, description, token)
    }

    /**
     * ## Updates an existing entry in the database.
     *
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     *
     * @param id          Unique identifier of the entry `36 chars`;
     * @param title       Short description of the entry `50 chars`;
     * @param description Full description of the entry `255 chars`;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun update(id: String,
               name: String,
               cpu: Int,
               ram: Int,
               ssd: Int,
               hdd: Int,
               running: Boolean,
               fmEntity: FmEntity,
               title: String,
               description: String,
               token: String
    ): ResponseEntity<*> {
        return e.update(id, name, cpu, ram, ssd, hdd, running, fmEntity, title, description, token)
    }

    /**
     * ## Soft deletion of all entities from the database.
     *
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     *
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun delete(id: String,
               token: String
    ): ResponseEntity<*> {
        return e.delete(id, token)
    }

    /**
     * ## Setting a new renter
     *
     * @param entityId Unique entity identifier
     * @param clientId New entity renter value
     * @param token JWT-Token for identification
     *
     * @author Anton Kuzmin
     * @since 2024.03.28
     * @since 2024.03.28
     */
    fun rent(entityId: String,
             clientId: Long,
             token: String
    ): ResponseEntity<*> {
        return r.rent(entityId, clientId, token)
    }

}