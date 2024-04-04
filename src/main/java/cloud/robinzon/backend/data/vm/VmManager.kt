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
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @see VmInsertUpdateForm
     * @since 2024.03.25
     */
    fun insert(form: VmInsertUpdateForm): ResponseEntity<*> {
        return e.insert(form)
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
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @see VmInsertUpdateForm
     * @since 2024.03.25
     */
    fun update(form: VmInsertUpdateForm): ResponseEntity<*> {
        return e.update(form)
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
     * @see VmDeleteForm
     * @since 2024.03.25
     */
    fun delete(form: VmDeleteForm): ResponseEntity<*> {
        return e.delete(form)
    }

    /**
     * ## Setting a new renter
     *
     * @author Anton Kuzmin
     * @see VmRentForm
     * @since 2024.03.28
     * @since 2024.03.28
     */
    fun rent(form: VmRentForm): ResponseEntity<*> {
        return r.rent(form)
    }

}