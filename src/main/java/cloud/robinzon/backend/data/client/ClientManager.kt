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

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.client.resources.ClientEntityManager
import cloud.robinzon.backend.data.client.resources.payment.ClientPaymentManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

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

@Service
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
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @see ClientInsertForm
     * @since 2024.03.25
     */
    fun insert(form: ClientInsertForm): ResponseEntity<*> {
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
     * @see ClientUpdateForm
     * @since 2024.03.25
     */
    fun update(form: ClientUpdateForm): ResponseEntity<*> {
        return e.update(form)
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
     * @return A standard response form
     * that contains the class name,
     * functions, status and text.
     * @author Anton Kuzmin
     * @see DeleteForm
     * @since 2024.03.25
     */
    fun delete(form: DeleteForm): ResponseEntity<*> {
        return e.delete(form)
    }

    /**
     * ## Setting a new balance value
     *
     * @author Anton Kuzmin
     * @since 2024.03.28
     * @see ClientBalanceForm
     * @since 2024.03.28
     */
    fun balance(form: ClientBalanceForm): ResponseEntity<*> {
        return p.balance(form)
    }

    /**
     * ## Payment
     *
     * This method changes the balance value.
     *
     * To write off 1000 units from the balance,
     * insert -1000 in the balance field
     *
     * @author Anton Kuzmin
     * @since 2024.03.28
     * @see ClientPayForm
     * @version 2024.03.28
     */
    fun pay(form: ClientPayForm): ResponseEntity<*> {
        return p.pay(form)
    }

}