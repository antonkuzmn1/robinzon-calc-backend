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

package cloud.robinzon.backend.data.fm.resources.rent

import cloud.robinzon.backend.common.Allow.check
import cloud.robinzon.backend.common.Log.*
import cloud.robinzon.backend.common.RentForm
import cloud.robinzon.backend.data.client.resources.ClientEntity
import cloud.robinzon.backend.data.client.resources.ClientEntityRepository
import cloud.robinzon.backend.data.fm.resources.FmEntity
import cloud.robinzon.backend.data.fm.resources.FmEntityRepository
import cloud.robinzon.backend.security.jwt.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FmRentManager(
    private val entityRepository: FmEntityRepository,
    private val rentRepository: FmRentRepository,
    private val clientEntityRepository: ClientEntityRepository,
    private val jwtUtil: JwtUtil
) {

    fun rent(form: RentForm): ResponseEntity<*> {

        set(javaClass, "rent")

        val changeBy = check(jwtUtil, form.token) ?: return err("Access denied")

        log("Entity search...")
        val entity: FmEntity = entityRepository.findById(form.entityId).orElse(null)
            ?: return err("Entity not found")
        log("Entity: ${entity.name}")

        log("Old renter: ${entity.client.name}")
        log("Client search")
        val client: ClientEntity = clientEntityRepository.findById(form.clientId).orElse(null)
            ?: return err("Client not found")
        if (entity.client.id.equals(client.id)) return err("All parameters are equal")
        log("New renter: ${client.name}")

        log("Saving entity...")
        entity.client = client
        entityRepository.save(entity)

        log("Saving rent history...")
        val rent = FmRent()
        rent.set(entity, changeBy, client)
        rentRepository.save(rent)

        log("Success!")
        return ResponseEntity.ok().body(rent)

    }
}