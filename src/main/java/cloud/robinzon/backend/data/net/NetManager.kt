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

package cloud.robinzon.backend.data.net

import cloud.robinzon.backend.data.net.resources.NetEntityManager
import cloud.robinzon.backend.data.net.resources.rent.NetRentManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * ## Entity Management Tools
 *
 * This class handles the management of Entities in the database.
 *
 * @author Anton Kuzmin
 * @see NetEntityManager
 * @see NetRentManager
 * @since 2024.03.28
 * @version 2024.03.28
 */

@Service
class NetManager(
        private val e: NetEntityManager,
        private val r: NetRentManager
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
     * @param domain      Domain name `50 chars`;
     * @param subnet      Subnet IP `15 chars`;
     * @param mask        Mask `15 chars`;
     * @param dns1        1st DNS IP `15 chars`;
     * @param dns2        2nd DNS IP `15 chars`;
     * @param dns3        3rd DNS IP `15 chars`;
     * @param cloud       Net is in cloud or not `15 chars`;
     * @param title       Short description of the entry `50 chars`;
     * @param description Full description of the entry `255 chars`;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun insert(domain: String,
               subnet: String,
               mask: String,
               dns1: String,
               dns2: String,
               dns3: String,
               cloud: Boolean,
               title: String,
               description: String,
               token: String
    ): ResponseEntity<*> {
        return e.insert(domain, subnet, mask, dns1, dns2, dns3, cloud, title, description, token)
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
     * @param id          Unique identifier of the entity;
     * @param subnet      Subnet IP `15 chars`;
     * @param mask        Mask `15 chars`;
     * @param dns1        1st DNS IP `15 chars`;
     * @param dns2        2nd DNS IP `15 chars`;
     * @param dns3        3rd DNS IP `15 chars`;
     * @param cloud       Net is in cloud or not `15 chars`;
     * @param title       Short description of the entry `50 chars`;
     * @param description Full description of the entry `255 chars`;
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun update(id: Long,
               domain: String,
               subnet: String,
               mask: String,
               dns1: String,
               dns2: String,
               dns3: String,
               cloud: Boolean,
               title: String,
               description: String,
               token: String
    ): ResponseEntity<*> {
        return e.update(id, domain, subnet, mask, dns1, dns2, dns3, cloud, title, description, token)
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
     * @param id - the unique identifier of the entity;
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