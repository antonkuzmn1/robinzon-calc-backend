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

package cloud.robinzon.backend.data.client

import cloud.robinzon.backend.data.client.resources.ClientEntityManager
import cloud.robinzon.backend.data.client.resources.payment.ClientPaymentManager
import org.springframework.http.ResponseEntity
import java.util.*

/**
 * ## Entity Management Tools
 *
 * This class handles the management of Entities in the database.
 *
 * @author Anton Kuzmin
 * @see ClientEntityManager
 * @see ClientPaymentManager
 * @since 2024.03.28
 * @version 2024.03.28
 */

class ClientManager(
        private val e: ClientEntityManager,
        private val p: ClientPaymentManager
) {

    /**
     * ## Inserts a new entry into the database.
     *2
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the new entity parameters and it will be updated.
     *
     * @param name           Client name {@code 50 chars};
     * @param inn            Client's TIN (INN) {@code 12 chars};
     * @param discount       Customer discount amount;
     * @param contractNumber Contract number;
     * @param contractDate   Date of conclusion of the contract;
     * @param title          Short description of the entry {@code 50 chars};
     * @param description    Full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun insert(name: String,
               inn: String,
               discount: Int,
               contractNumber: Int,
               contractDate: Date,
               title: String,
               description: String,
               token: String
    ): ResponseEntity<*> {
        return e.insert(name, inn, discount, contractNumber, contractDate, title, description, token)
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
     * @param name           Client name {@code 50 chars};
     * @param inn            Client's TIN (INN) {@code 12 chars};
     * @param discount       Customer discount amount;
     * @param contractNumber Contract number;
     * @param contractDate   Date of conclusion of the contract;
     * @param title          Short description of the entry {@code 50 chars};
     * @param description    Full description of the entry {@code 255 chars};
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    fun update(id: Long,
               name: String,
               inn: String,
               discount: Int,
               contractNumber: Int,
               contractDate: Date,
               title: String,
               description: String,
               token: String
    ): ResponseEntity<*> {
        return e.update(id, name, inn, discount, contractNumber, contractDate, title, description, token)
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
     * ## Setting a new balance value
     *
     * @param id Unique entity identifier
     * @param balance New entity balance value
     * @param token JWT-Token for identification
     *
     * @author Anton Kuzmin
     * @since 2024.03.28
     * @since 2024.03.28
     */
    @Suppress("unused")
    fun balance(id: Long,
                balance: Int,
                token: String
    ): ResponseEntity<*> {
        return p.balance(id, balance, token)
    }

    /**
     * ## Payment
     *
     * This method changes the balance value.
     *
     * To write off 1000 units from the balance,
     * insert -1000 in the balance field
     *
     * @param id Unique entity identifier
     * @param sum Difference
     * @param token JWT-Token for identification
     *
     * @author Anton Kuzmin
     * @since 2024.03.28
     * @version 2024.03.28
     */
    @Suppress("unused")
    fun pay(id: Long,
            sum: Int,
            token: String
    ): ResponseEntity<*> {
        return p.pay(id, sum, token)
    }

}