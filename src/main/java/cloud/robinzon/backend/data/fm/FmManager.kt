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

package cloud.robinzon.backend.data.fm

import cloud.robinzon.backend.data.fm.resources.FmEntityManager
import cloud.robinzon.backend.data.fm.resources.rent.FmRentManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * ## Entity Management Tools
 *
 * This class handles the management of Entities in the database.
 *
 * @author Anton Kuzmin
 * @see FmEntityManager
 * @see FmRentManager
 * @since 2024.03.28
 * @version 2024.03.28
 */

@Service
class FmManager(
        private val e: FmEntityManager,
        private val r: FmRentManager
) {

    /**
     * ## Inserts a new entry into the database.
     *
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the new entity parameters and it will be updated.
     *
     * @param name           Name of the entity `50 chars`;
     * @param ip             IP address of the entity `15 chars`;
     * @param title          Short description of the entry `50 chars`;
     * @param specifications Specifications of the entry `255 chars`;
     * @param description    Full description of the entry `255 chars`;
     * @param price          Price of the entity;
     * @param vm             FM can host VM's or not;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun insert(name: String,
               ip: String,
               title: String,
               specifications: String,
               description: String,
               price: Int,
               vm: Boolean,
               token: String
    ): ResponseEntity<*> {
        return e.insert(name, ip, title, specifications, description, price, vm, token)
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
     * @param id             Unique identifier of the entity;
     * @param ip             IP address of the entity `15 chars`;
     * @param title          Short description of the entry `50 chars`;
     * @param specifications Specifications of the entry `255 chars`;
     * @param description    Full description of the entry `255 chars`;
     * @param price          Price of the entity;
     * @param vm             FM can host VM's or not;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun update(id: Long,
               name: String,
               ip: String,
               title: String,
               specifications: String,
               description: String,
               price: Int,
               vm: Boolean,
               token: String
    ): ResponseEntity<*> {
        return e.update(id, name, ip, title, specifications, description, price, vm, token)
    }

    /**
     * ## Deleting an existing entry in the database.
     *
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     *
     * @param id Unique identifier of the entity;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun delete(id: Long,
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
    fun rent(entityId: Long,
             clientId: Long,
             token: String
    ): ResponseEntity<*> {
        return r.rent(entityId, clientId, token)
    }

}