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

package cloud.robinzon.backend.data.client.resources.payment

import cloud.robinzon.backend.security.tools.isAdmin
import cloud.robinzon.backend.common.Log.*
import cloud.robinzon.backend.data.client.resources.ClientEntity
import cloud.robinzon.backend.data.client.resources.ClientEntityRepository
import cloud.robinzon.backend.security.user.resources.UserEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import cloud.robinzon.backend.data.client.ClientManager

/**
 * Payment management
 *
 * @author Anton Kuzmin
 * @see ClientManager
 * @since 2024.03.28
 * @version 2024.03.28
 */

@Service
class ClientPaymentManager(
        private val paymentRepository: ClientPaymentRepository,
        private val entityRepository: ClientEntityRepository
) {

    private fun entity(id: Long): ClientEntity? {
        log("Entity search...")
        return entityRepository.findById(id).orElse(null)
    }

    private fun balance(entity: ClientEntity): Int {
        val balance: Int = paymentRepository.getBalance(entity.id)
        log("Current balance: $balance")
        return balance
    }

    private fun template(methodName: String,
                         entity: ClientEntity,
                         balance: Int,
                         token: String
    ): ResponseEntity<*> {
        set(javaClass, methodName)
        val changeBy: UserEntity = isAdmin(token) ?: return err("Access denied")
        log("Client: ${entity.name}")

        val oldBalance: Int = balance(entity)
        log("New balance: $balance")

        log("Checks...")
        if (balance == oldBalance) return err("All parameters are equal")

        val payment = ClientPayment(entity, balance, changeBy)

        log("Saving entity...")
        entity.balance = balance
        entityRepository.save(entity)

        log("Saving payment...")
        paymentRepository.save(payment)

        log("Success!")
        return ResponseEntity.ok().body(payment)
    }

    fun balance(id: Long,
               balance: Int,
               token: String
    ): ResponseEntity<*> {
        val entity: ClientEntity = entity(id) ?: return err("Entity not found")

        return template("update", entity, balance, token)
    }

    fun pay(id: Long,
            sum: Int,
            token: String
    ): ResponseEntity<*> {
        if (sum == 0) return err("Pay sum cannot be null")
        val entity: ClientEntity = entity(id) ?: return err("Entity not found")
        val balance: Int = balance(entity) + sum

        return template("pay", entity, balance, token)
    }

}